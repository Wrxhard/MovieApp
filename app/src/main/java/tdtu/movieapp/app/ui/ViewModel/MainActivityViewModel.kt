package tdtu.movieapp.app. ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.MultipartBody
import okhttp3.RequestBody
import tdtu.movieapp.app.data.remote.service.network.MainRepository
import tdtu.movieapp.app.data.local.service.LocalRepo
import tdtu.movieapp.app.data.model.Movies.Category
import tdtu.movieapp.app.data.model.Movies.Movie
import tdtu.movieapp.app.data.model.Movies.RecentlyMovie
import tdtu.movieapp.app.data.model.Movies.UserAuth
import tdtu.movieapp.app.utils.DispatcherProvider
import tdtu.movieapp.app.utils.Resource
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val localRepo: LocalRepo,
    private val respository: MainRepository,
    private val dispatcher: DispatcherProvider
): ViewModel(){
    private var jobs: Job? =null
    private var RewatchJob :Job? = null
    //Check state of action
    sealed class Event<out T> {
        class Success<T>(val result: T): Event<T>()
        class Failure(val error: String): Event<Nothing>()
        object Loading : Event<Nothing>()
        object Empty : Event<Nothing>()
    }

    private val _login = MutableStateFlow<Event<UserAuth>>(Event.Empty)
    val loginevent: StateFlow<Event<UserAuth>> = _login

    private val _register = MutableStateFlow<Event<UserAuth>>(Event.Empty)
    val registerevent: StateFlow<Event<UserAuth>> = _register

    private val _movies = MutableStateFlow<Event<List<Movie>>>(Event.Empty)
    val movies=_movies.asStateFlow()

    private val _movies2 = MutableStateFlow<Event<List<Movie>>>(Event.Empty)
    val movies2=_movies2.asStateFlow()

    private val _moviesall = MutableStateFlow<List<Movie>>(mutableListOf())
    val moviesall=_moviesall.asStateFlow()

    private val _searchList = MutableStateFlow<List<Movie>>(mutableListOf())
    val searchList=_searchList.asStateFlow()

    private val _recentlyWatch= MutableStateFlow<Event<List<RecentlyMovie>>>(Event.Empty)
    val recentlyWatch=_recentlyWatch.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(true)
    val loading=_loading.asStateFlow()

    fun addRecentlyWatch(movie: RecentlyMovie)
    {
        viewModelScope.launch{
            localRepo.insertMovie(movie)
        }
    }
    fun clearRecenlyWatch()
    {
        viewModelScope.launch {
            localRepo.deleteAll()
        }
        getRecentlyWatch()
    }
    fun getRecentlyWatch() {
        RewatchJob?.cancel()
        RewatchJob=localRepo.getLocalMovies()
            .onEach {
                _recentlyWatch.value=Event.Success(it)
            }
            .launchIn(viewModelScope)
    }

    fun addFullList(list:List<Movie>)
    {
        _moviesall.value+=list
    }
    fun clearFullList()
    {
        _moviesall.value= mutableListOf()
    }
    fun filterSearch(query:String)
    {
        _searchList.value=_moviesall.value.filter {
            it.title.contains(query) || it.movie_genres.contains(Category(
                query
            ))
        }
        _searchList.value=_searchList.value.toSet().toList()
    }
    fun clearSearch()
    {
        _searchList.value= mutableListOf()
    }

    fun login(username:String,password:String) {

        //add username and password to request body
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", username)
            .addFormDataPart("password", password)
            .build()

        //start perform post request
        viewModelScope.launch(dispatcher.io){
            val res=respository.login(requestBody)
            _login.value= Event.Loading
            when(res){
                is Resource.Success ->  {
                    if (res.data!=null && res.data.status)
                    {
                        _login.value = Event.Success(res.data)
                    }
                    else
                    {
                        _login.value = Event.Failure("Incorrect Username Or Password")
                    }
                }
                is Resource.Error -> _login.value = Event.Failure("Connect To Server Failure")
            }
        }
    }
    fun register(firstname:String,lastname:String,username:String,email:String,password:String) {

        //add username and password to request body
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("firstName", firstname)
            .addFormDataPart("lastName", lastname)
            .addFormDataPart("username", username)
            .addFormDataPart("email", email)
            .addFormDataPart("password", password)
            .build()

        //start perform post request
        viewModelScope.launch(dispatcher.io){
            val res=respository.register(requestBody)
            _register.value= Event.Loading
            when(res){
                is Resource.Success ->  {
                    if (res.data!=null && res.data.status)
                    {
                        _register.value = Event.Success(res.data)
                    }
                    else
                    {
                        _register.value = Event.Failure("Failed to register")
                    }
                }
                is Resource.Error -> _register.value = Event.Failure("Connect Failure")
            }
        }
    }

    //Perform call api and get movies
    private suspend fun getMovies(page:Int): Event<List<Movie>> {
        _loading.value=true

        return suspendCancellableCoroutine {  continues ->
            jobs=viewModelScope.launch(dispatcher.io){
                val res=respository.getMovies(page)
                when(res){
                    is Resource.Success ->  {
                        if (res.data!=null)
                        {
                            _loading.value=false
                            continues.resume(Event.Success(res.data))
                        }
                        else{
                            _loading.value=false
                            continues.resume(Event.Empty)
                        }

                    }
                    is Resource.Error ->
                    {
                        _loading.value=false
                        continues.resume(Event.Failure("Connect Failure Check Your Internet Connection"))
                    }
                }
                continues.invokeOnCancellation {
                        jobs=null
                }
            }
        }
    }
    fun cancel()
    {
        jobs?.cancel()
    }
    suspend fun getPopular(page: Int){
        if (_movies.value==Event.Empty)
        {
            _movies.value=Event.Loading
            _movies.value=getMovies(page)
        }
    }
    suspend fun getTrending(page: Int)
    {
        if (_movies2.value==Event.Empty)
        {
            _movies2.value=Event.Loading
            _movies2.value=getMovies(page)
        }
    }
}
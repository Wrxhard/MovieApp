package tdtu.movieapp.app. ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import tdtu.movieapp.app.data.MainRepository
import tdtu.movieapp.app.data.model.Movies.Movie
import tdtu.movieapp.app.utils.DispatcherProvider
import tdtu.movieapp.app.utils.Resource
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val respository: MainRepository,
    private val dispatcher: DispatcherProvider
): ViewModel(){
    //Check state of action
    sealed class Event {
        class Success(val result: List<Movie>): Event()
        class Failure(val error: String): Event()
        object Loading : Event()
        object Empty : Event()
    }
    private val _movies = MutableStateFlow<Event>(Event.Empty)
    val movies=_movies.asStateFlow()
    private val _movies2 = MutableStateFlow<Event>(Event.Empty)
    val movies2=_movies2.asStateFlow()
    private val _loading = MutableStateFlow<Boolean>(true)
    val loading=_loading.asStateFlow()

    //Perform call api and get movies
    private suspend fun getMovies(page:Int): Event {
        return suspendCancellableCoroutine {  continues ->
            viewModelScope.launch(dispatcher.io){
                val res=respository.getMovies(page)
                _movies.value= Event.Loading
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
                        continues.resume(Event.Failure("Connect Failure"))
                    }
                }
            }
        }
    }
    suspend fun getPopular(page: Int){
        _movies.value=getMovies(page)
    }
    suspend fun getTrending(page: Int)
    {
        _movies2.value=getMovies(page)
    }
}
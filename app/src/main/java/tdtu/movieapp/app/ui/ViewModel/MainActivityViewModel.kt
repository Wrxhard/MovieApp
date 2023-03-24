package tdtu.movieapp.app. ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tdtu.movieapp.app.data.MainRepository
import tdtu.movieapp.app.data.model.Treding.Movie
import tdtu.movieapp.app.utils.DispatcherProvider
import tdtu.movieapp.app.utils.Resource
import javax.inject.Inject

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
    // getter for movies movie list
    val movies=_movies.asStateFlow()
    private val _loading = MutableStateFlow<Boolean>(true)
    val loading=_loading.asStateFlow()

    //Perform call api and get movies
    fun getMovies(page:Int){
        viewModelScope.launch(dispatcher.io){
            val res=respository.getMovies(page)
            _movies.value= Event.Loading
            when(res){
                is Resource.Success ->  {
                    if (res.data!=null)
                    {
                        _movies.value = Event.Success(res.data)
                        _loading.value=false
                    }
                    else{
                        val temp:List<Movie> = emptyList()
                        _movies.value=Event.Success(temp)
                        _loading.value=false
                    }

                }
                is Resource.Error ->
                {
                    _movies.value = Event.Failure("Connect Failure")
                    _loading.value=false
                }
            }
        }
    }
}
package tdtu.movieapp.app. ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tdtu.movieapp.app.data.MainRepository
import tdtu.movieapp.app.data.model.Treding.TredingMovie
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
        class Success(val result: List<TredingMovie>): Event()
        class Failure(val error: String): Event()
        object Loading : Event()
        object Empty : Event()
    }
    private val _trending = MutableStateFlow<Event>(Event.Empty)
    // getter for trending movie list
    val treding=_trending.asStateFlow()
    private val _loading = MutableStateFlow<Boolean>(true)
    val loading=_loading.asStateFlow()

    //Perform call api and get trending list with page number
    fun getTrending(page:Int){
        viewModelScope.launch(dispatcher.io){
            val res=respository.getPopularMovie("0fa22dd55121fefae802e6954568d282",page)
            _trending.value= Event.Loading
            when(res){
                is Resource.Success ->  {
                    if (res.data!=null)
                    {
                        _trending.value = Event.Success(res.data.tredingMovies)
                        _loading.value=false
                    }
                    else{
                        val temp:List<TredingMovie> = emptyList()
                        _trending.value=Event.Success(temp)
                        _loading.value=false
                    }

                }
                is Resource.Error ->
                {
                    _trending.value = Event.Failure("Connect Failure")
                    _loading.value=false
                }
            }
        }
    }
}
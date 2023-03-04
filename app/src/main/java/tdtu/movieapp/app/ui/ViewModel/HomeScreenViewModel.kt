package tdtu.movieapp.app.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Response
import tdtu.movieapp.app.data.remote.MovieList
import tdtu.movieapp.app.utils.network.RetrofitInstance
import tdtu.movieapp.app.utils.network.RetroService

class HomeScreenViewModel:ViewModel() {
    private lateinit var trending: LiveData<Response<MovieList>>
    private val retrofitService: RetroService

    init {
        retrofitService= RetrofitInstance
            .getRetroIns()
            .create(RetroService::class.java)
    }

    fun getTrending():LiveData<Response<MovieList>>{
        trending= liveData {
            val response=retrofitService.getPopularMovie("0fa22dd55121fefae802e6954568d282",1)
            emit(response)
        }
        return trending
    }
}
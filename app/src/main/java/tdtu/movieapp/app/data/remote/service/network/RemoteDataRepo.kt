package tdtu.movieapp.app.data.remote.service.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import retrofit2.Response
import tdtu.movieapp.app.data.model.Treding.TredingMovieList

class RemoteDataRepo() {
    private lateinit var trending: LiveData<Response<TredingMovieList>>
    private val retrofitService: TmdbService

    init {
        retrofitService= RetrofitInstance
            .getRetroIns()
            .create(TmdbService::class.java)
    }

    fun getTrending(page:Int): LiveData<Response<TredingMovieList>> {
        trending= liveData {
            val response=retrofitService.getPopularMovie("0fa22dd55121fefae802e6954568d282",page)
            emit(response)
        }
        return trending
    }
}
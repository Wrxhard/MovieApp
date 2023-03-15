package tdtu.movieapp.app.ui.ViewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import tdtu.movieapp.app.data.model.Treding.TredingMovieList
import tdtu.movieapp.app.data.remote.service.network.RetrofitInstance
import tdtu.movieapp.app.data.remote.service.network.TmdbService
import tdtu.movieapp.app.ui.Model.ListFilmModel

class MainActivityViewModel:ViewModel() {
    private lateinit var trending: LiveData<Response<TredingMovieList>>
    private val retrofitService: TmdbService
    private var listTrendingFilm = MutableLiveData<List<ListFilmModel>>()
    val listTrending: LiveData<List<ListFilmModel>>
        get() {
        return listTrendingFilm
        }

    init {
        retrofitService= RetrofitInstance
            .getRetroIns()
            .create(TmdbService::class.java)
    }

    fun getTrending(page:Int): LiveData<Response<TredingMovieList>> {
        trending= liveData(Dispatchers.IO) {
            val response=retrofitService.getPopularMovie("0fa22dd55121fefae802e6954568d282",page)
            emit(response)
        }
        return trending
    }

    fun setListTrending(list:List<ListFilmModel>){
        this.listTrendingFilm.value =list
    }
}
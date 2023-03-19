package tdtu.movieapp.app.data

import retrofit2.http.Query
import tdtu.movieapp.app.data.model.Treding.TredingMovieList
import tdtu.movieapp.app.utils.Resource

interface MainRepository {
    suspend fun getPopularMovie(@Query("quantity") quantity:Int): Resource<TredingMovieList>
}
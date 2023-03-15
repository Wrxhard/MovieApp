package tdtu.movieapp.app.data

import retrofit2.Response
import retrofit2.http.Query
import tdtu.movieapp.app.data.model.Treding.TredingMovieList
import tdtu.movieapp.app.utils.Resource

interface MainRepository {
    suspend fun getPopularMovie(@Query("api_key") api_key:String, @Query("page") page:Int): Resource<TredingMovieList>
}
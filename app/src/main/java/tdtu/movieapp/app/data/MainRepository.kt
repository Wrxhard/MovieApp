package tdtu.movieapp.app.data

import retrofit2.http.Query
import tdtu.movieapp.app.data.model.Movies.Movies
import tdtu.movieapp.app.utils.Resource

interface MainRepository {
    suspend fun getMovies(@Query("page") page:Int): Resource<Movies>
}
package tdtu.movieapp.app.data.remote.service.network

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Query
import tdtu.movieapp.app.data.model.Movies.Movies
import tdtu.movieapp.app.data.model.Movies.UserAuth
import tdtu.movieapp.app.utils.helper.Resource

interface MainRepository {
    suspend fun getMovies(@Query("page") page:Int): Resource<Movies>
    suspend fun login(@Body body: RequestBody): Resource<UserAuth>
    suspend fun register(@Body body: RequestBody): Resource<UserAuth>
    suspend fun loginGoogle(@Body body: RequestBody): Resource<UserAuth>
}
package tdtu.movieapp.app.data.remote.service.network
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tdtu.movieapp.app.data.model.Movies.Movies

interface NetworkService {
    @GET("/movies")
    suspend fun getMovies(@Query("page") page:Int):Response<Movies>
}
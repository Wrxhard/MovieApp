package tdtu.movieapp.app.utils.network
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tdtu.movieapp.app.data.remote.MovieList

interface RetroService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovie(@Query("api_key") api_key:String,@Query("page") page:Int):Response<MovieList>
}
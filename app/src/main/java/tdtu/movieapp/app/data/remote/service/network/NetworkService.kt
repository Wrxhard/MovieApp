package tdtu.movieapp.app.data.remote.service.network
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import tdtu.movieapp.app.data.model.Movies.Movies
import tdtu.movieapp.app.data.model.Movies.UserAuth

interface NetworkService {
    @GET("/movies")
    suspend fun getMovies(@Query("page") page:Int):Response<Movies>

    @POST("/loginonmobile")
    suspend fun login(@Body body: RequestBody):Response<UserAuth>

    @POST("/register")
    suspend fun register(@Body body: RequestBody):Response<UserAuth>
}
package tdtu.movieapp.app.data.remote.service.network
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tdtu.movieapp.app.data.model.Treding.TredingMovieList

interface TmdbService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovie(@Query("api_key") api_key:String,@Query("page") page:Int):Response<TredingMovieList>
}
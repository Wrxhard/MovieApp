package tdtu.movieapp.app.data.remote.service.network
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tdtu.movieapp.app.data.model.Treding.TredingMovieList

interface TmdbService {
    @GET("/movies")
    suspend fun getPopularMovie(@Query("quantity") quantity:Int):Response<TredingMovieList>
}
package tdtu.movieapp.app.utils.service
import tdtu.movieapp.app.data.remote.Albums
import retrofit2.Response
import retrofit2.http.GET

interface RetroService {

    @GET("/abulms")
    suspend fun getAlbum():Response<Albums>
}
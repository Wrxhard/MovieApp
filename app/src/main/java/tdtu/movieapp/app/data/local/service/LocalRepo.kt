package tdtu.movieapp.app.data.local.service

import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE
import tdtu.movieapp.app.data.model.Movies.Movie

interface LocalRepo {

    suspend fun insertMovie(movie: Movie)

    fun getLocalMovies(): Flow<List<Movie>>

    suspend fun getLocalMovie(id:String): Movie?

    suspend fun deleteAll()

}
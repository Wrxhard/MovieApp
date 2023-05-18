package tdtu.movieapp.app.data.local.service

import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE
import tdtu.movieapp.app.data.model.Movies.Movie
import tdtu.movieapp.app.data.model.Movies.RecentlyMovie

interface LocalRepo {

    suspend fun insertMovie(movie: RecentlyMovie)

    fun getLocalMovies(): Flow<List<RecentlyMovie>>

    suspend fun getLocalMovie(id:String): RecentlyMovie?

    suspend fun deleteAll()

}
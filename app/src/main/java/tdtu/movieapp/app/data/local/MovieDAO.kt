package tdtu.movieapp.app.data.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE
import tdtu.movieapp.app.data.model.Movies.Movie
import tdtu.movieapp.app.data.model.Movies.RecentlyMovie

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: RecentlyMovie)

    @Query("SELECT * FROM Recent_movie_table")
    fun getLocalMovies(): Flow<List<RecentlyMovie>>

    @Query("SELECT * FROM Recent_movie_table WHERE id = :id")
    suspend fun getLocalMovie(id:String): RecentlyMovie?

    @Query("DELETE FROM Recent_movie_table")
    suspend fun deleteAll()

}
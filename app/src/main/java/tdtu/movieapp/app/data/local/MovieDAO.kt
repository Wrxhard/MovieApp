package tdtu.movieapp.app.data.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE
import tdtu.movieapp.app.data.model.Movies.Movie

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie_table")
    fun getLocalMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    suspend fun getLocalMovie(id:String): Movie?

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()

}
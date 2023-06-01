package tdtu.movieapp.app.data.local.service
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
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
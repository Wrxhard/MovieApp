package tdtu.movieapp.app.data.model.Treding

import androidx.room.*

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies:List<Movie>)
    @Query("DELETE FROM movies")
    suspend fun deleteAll()
    @Query("SELECT * FROM movies")
    suspend fun getMovies():List<Movie>

}
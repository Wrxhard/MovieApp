package tdtu.movieapp.app.data.model.Treding

import androidx.room.*

@Dao
interface TredingFilmDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTredingMovie(movies:List<TredingMovie>)
    @Query("DELETE FROM trending_movies")
    suspend fun deleteAllTreding()
    @Query("SELECT * FROM trending_movies")
    suspend fun getAllTreding():List<TredingMovie>

}
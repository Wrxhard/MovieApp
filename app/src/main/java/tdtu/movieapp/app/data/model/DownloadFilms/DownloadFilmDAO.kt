package tdtu.movieapp.app.data.model.DownloadFilms

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DownloadFilmDAO {

    @Insert
    suspend fun insertFilm(film: DownloadFilm)
    @Query("SELECT * FROM download_films_table")
    suspend fun getAllFilms(): List<DownloadFilm>
    @Update
    suspend fun updateFilm(film: DownloadFilm)
    @Delete
    suspend fun deleteFilm(film: DownloadFilm)
    @Query("DELETE FROM download_films_table")
    suspend fun deleteAll()

}
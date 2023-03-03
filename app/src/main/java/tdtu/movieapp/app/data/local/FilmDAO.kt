package tdtu.movieapp.app.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FilmDAO {

    @Insert
    suspend fun insertFilm(film: tdtu.movieapp.app.data.local.Film)

    @Query("SELECT * FROM films_table")
    fun getAllFilms(): LiveData<List<tdtu.movieapp.app.data.local.Film>>

    @Update
    suspend fun updateFilm(film: tdtu.movieapp.app.data.local.Film)

    @Delete
    suspend fun deleteFilm(film: tdtu.movieapp.app.data.local.Film)

    @Query("DELETE FROM films_table")
    suspend fun deleteAll()

}
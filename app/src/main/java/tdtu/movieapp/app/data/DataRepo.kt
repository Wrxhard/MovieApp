package tdtu.movieapp.app.data

import tdtu.movieapp.app.data.local.Film
import tdtu.movieapp.app.data.local.FilmDAO

class DataRepo(private val FilmDao: FilmDAO)
{
    val films=FilmDao.getAllFilms()

    suspend fun insertFilm(film: Film)
    {
        FilmDao.insertFilm(film)
    }
    suspend fun updateFilm(film: Film)
    {
        FilmDao.updateFilm(film)
    }
    suspend fun deleteFilm(film: Film)
    {
        FilmDao.deleteFilm(film)
    }
    suspend fun deleteAll()
    {
        FilmDao.deleteAll()
    }
}
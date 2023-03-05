package tdtu.movieapp.app.data.local.service.DownloadFilms

import tdtu.movieapp.app.data.model.DownloadFilms.DownloadFilm

interface DownloadFilmsRepo{
    suspend fun getAllFilms(): List<DownloadFilm>?
    suspend fun updateFilm():List<DownloadFilm>
}


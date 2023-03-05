package tdtu.movieapp.app.data.local.service.DownloadFilms

import tdtu.movieapp.app.data.model.DownloadFilms.DownloadFilm

class GetAllDownloadFilm(private val downloadFilmsRepo: DownloadFilmsRepo) {
    suspend fun excute():List<DownloadFilm>? = downloadFilmsRepo.getAllFilms()
}
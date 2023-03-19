package tdtu.movieapp.app.data.local.service.Trending

import tdtu.movieapp.app.data.model.Treding.Movie

class SaveAllTreding(private val tredingFilmsRepo: TredingFilmsRepo) {
    suspend fun excute(): List<Movie> = tredingFilmsRepo.saveMovies()
}
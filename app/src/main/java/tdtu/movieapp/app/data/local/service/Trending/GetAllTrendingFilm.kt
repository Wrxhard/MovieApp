package tdtu.movieapp.app.data.local.service.Trending

import tdtu.movieapp.app.data.model.Treding.Movie

class GetAllTrendingFilm(private val trendingFilmsRepo:TredingFilmsRepo) {
    suspend fun excute(): List<Movie> = trendingFilmsRepo.getAllMovies()
}
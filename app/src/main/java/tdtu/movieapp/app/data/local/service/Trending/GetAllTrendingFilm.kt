package tdtu.movieapp.app.data.local.service.Trending

import tdtu.movieapp.app.data.model.Treding.TredingMovie

class GetAllTrendingFilm(private val trendingFilmsRepo:TredingFilmsRepo) {
    suspend fun excute():List<TredingMovie>? = trendingFilmsRepo.getAllTreding()
}
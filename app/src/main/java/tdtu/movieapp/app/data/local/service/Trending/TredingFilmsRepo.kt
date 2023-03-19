package tdtu.movieapp.app.data.local.service.Trending


import tdtu.movieapp.app.data.model.Treding.Movie

interface TredingFilmsRepo {

    suspend fun getAllMovies():List<Movie>
    suspend fun saveMovies():List<Movie>

}
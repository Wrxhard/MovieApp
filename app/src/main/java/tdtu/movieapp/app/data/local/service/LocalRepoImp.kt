package tdtu.movieapp.app.data.local.service

import kotlinx.coroutines.flow.Flow
import tdtu.movieapp.app.data.local.MovieDAO
import tdtu.movieapp.app.data.model.Movies.Movie
import javax.inject.Inject

class LocalRepoImp(
    private val dao: MovieDAO
):LocalRepo {
    override suspend fun insertMovie(movie: Movie) {
        dao.insertMovie(movie)
    }

    override fun getLocalMovies(): Flow<List<Movie>> {
        return dao.getLocalMovies()
    }

    override suspend fun getLocalMovie(id: String): Movie? {
        return dao.getLocalMovie(id)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

}
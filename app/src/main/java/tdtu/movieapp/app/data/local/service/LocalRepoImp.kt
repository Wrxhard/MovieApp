package tdtu.movieapp.app.data.local.service

import kotlinx.coroutines.flow.Flow
import tdtu.movieapp.app.data.local.MovieDAO
import tdtu.movieapp.app.data.model.Movies.Movie
import tdtu.movieapp.app.data.model.Movies.RecentlyMovie
import javax.inject.Inject

class LocalRepoImp(
    private val dao: MovieDAO
):LocalRepo {
    override suspend fun insertMovie(movie: RecentlyMovie) {
        dao.insertMovie(movie)
    }

    override fun getLocalMovies(): Flow<List<RecentlyMovie>> {
        return dao.getLocalMovies()
    }

    override suspend fun getLocalMovie(id: String): RecentlyMovie? {
        return dao.getLocalMovie(id)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

}
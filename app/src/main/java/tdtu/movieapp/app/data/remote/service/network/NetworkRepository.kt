package tdtu.movieapp.app.data.remote.service.network

import tdtu.movieapp.app.data.MainRepository
import tdtu.movieapp.app.data.model.Treding.TredingMovieList
import tdtu.movieapp.app.utils.Resource
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val api:TmdbService
):MainRepository {
    override suspend fun getPopularMovie(api_key: String, page: Int): Resource<TredingMovieList> {
        return try {
            val respone=api.getPopularMovie(api_key,page)
            val res=respone.body()
            if (respone.isSuccessful && res!=null)
            {
                Resource.Success(res)
            }
            else
            {
                Resource.Error(respone.message())
            }

        }catch (e:Exception)
        {
            Resource.Error(e.message ?: "An error occured")
        }

    }
}
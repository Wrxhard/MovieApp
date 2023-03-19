package tdtu.movieapp.app.data.remote.service.network
import tdtu.movieapp.app.data.MainRepository
import tdtu.movieapp.app.data.model.Treding.Movies
import tdtu.movieapp.app.utils.Resource
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val api:NetworkService
):MainRepository {
    override suspend fun getMovies(quantity:Int): Resource<Movies> {
        return try {
            val respone=api.getMovies(quantity)
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
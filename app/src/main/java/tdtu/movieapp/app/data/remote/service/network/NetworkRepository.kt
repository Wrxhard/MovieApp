package tdtu.movieapp.app.data.remote.service.network
import okhttp3.RequestBody
import retrofit2.http.Body
import tdtu.movieapp.app.data.model.Movies.Movies
import tdtu.movieapp.app.data.model.Movies.UserAuth
import tdtu.movieapp.app.utils.helper.Resource
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val api:NetworkService
): MainRepository {
    override suspend fun getMovies(page:Int): Resource<Movies> {
        return try {
            val respone=api.getMovies(page)
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
    override suspend fun login(@Body body: RequestBody): Resource<UserAuth> {
        return try {
            val respone=api.login(body)
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
    override suspend fun register(@Body body: RequestBody): Resource<UserAuth> {
        return try {
            val respone=api.register(body)
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

    override suspend fun loginGoogle(@Body body: RequestBody): Resource<UserAuth> {
        return try {
            val respone=api.loginGoogle(body)
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
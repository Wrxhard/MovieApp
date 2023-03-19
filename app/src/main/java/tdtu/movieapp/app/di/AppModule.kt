package tdtu.movieapp.app.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tdtu.movieapp.app.data.MainRepository
import tdtu.movieapp.app.data.remote.service.network.NetworkRepository
import tdtu.movieapp.app.data.remote.service.network.NetworkService
import tdtu.movieapp.app.utils.DispatcherProvider
import javax.inject.Singleton

val BASE_URL="http://192.168.21.220:80/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(): NetworkService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(NetworkService::class.java)
    @Singleton
    @Provides
    fun provideMainRepository(api: NetworkService): MainRepository = NetworkRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

}
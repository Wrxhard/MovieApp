package tdtu.movieapp.app.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tdtu.movieapp.app.data.model.DownloadFilms.DownloadFilm
import tdtu.movieapp.app.data.model.DownloadFilms.DownloadFilmDAO
import tdtu.movieapp.app.data.model.Treding.TredingFilmDAO
import tdtu.movieapp.app.data.model.Treding.TredingMovie

@Database(entities = [DownloadFilm::class, TredingMovie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun DownloadFilmDao(): DownloadFilmDAO
    abstract fun TredingFilmDAO():TredingFilmDAO
    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase?=null
        fun getInstance(context: Context): MovieDatabase {
            synchronized(this){
                var instance= INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "films_database"
                    ).build()
                    INSTANCE =instance
                }
                return instance
            }
        }
    }
}
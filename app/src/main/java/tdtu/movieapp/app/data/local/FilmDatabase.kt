package tdtu.movieapp.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [tdtu.movieapp.app.data.local.Film::class], version = 1)
abstract class FilmDatabase: RoomDatabase() {
    abstract val FilmDao: tdtu.movieapp.app.data.local.FilmDAO

    companion object{
        @Volatile
        private var INSTANCE: tdtu.movieapp.app.data.local.FilmDatabase?=null
        fun getInstance(context: Context): tdtu.movieapp.app.data.local.FilmDatabase {
            synchronized(this){
                var instance= tdtu.movieapp.app.data.local.FilmDatabase.Companion.INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        tdtu.movieapp.app.data.local.FilmDatabase::class.java,
                        "book_database"
                    ).build()
                    tdtu.movieapp.app.data.local.FilmDatabase.Companion.INSTANCE =instance
                }
                return instance
            }
        }
    }
}
package tdtu.movieapp.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tdtu.movieapp.app.data.model.Movies.Movie
import tdtu.movieapp.app.data.model.Movies.RecentlyMovie

@Database(
    entities = [RecentlyMovie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class DatabaseIns:RoomDatabase() {
    abstract fun getDatabaseDAO():MovieDAO
}
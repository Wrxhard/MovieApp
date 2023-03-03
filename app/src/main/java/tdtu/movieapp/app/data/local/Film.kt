package tdtu.movieapp.app.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_table")
data class Film(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name="films_name")
    var name: String,
    @ColumnInfo(name = "film_image")
    var image: Int,
    @ColumnInfo(name = "film_category")
    var category: String
)
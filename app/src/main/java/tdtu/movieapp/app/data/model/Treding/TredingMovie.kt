package tdtu.movieapp.app.data.model.Treding

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trending_movies")
data class TredingMovie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @ColumnInfo("movie_poster")
    @SerializedName("poster_path")
    val poster_path: String,
    @ColumnInfo("movie_title")
    @SerializedName("title")
    val title: String,
)
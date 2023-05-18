package tdtu.movieapp.app.data.model.Movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    "Recent_movie_table"
)
data class RecentlyMovie(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("trailer")
    val trailer:String,
    @SerializedName("view_time")
    val view_time:Float
)

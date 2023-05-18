package tdtu.movieapp.app.data.model.Movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("id")
    val id: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("release_date")
    val release_date:String,
    @SerializedName("director_id")
    val director_id: String,
    @SerializedName("imdb_id")
    val imdb_id:String,
    @SerializedName("score")
    val score:Float,
    @SerializedName("trailer")
    val trailer:String,
    @SerializedName("movie_genres")
    val movie_genres:List<Category>
)
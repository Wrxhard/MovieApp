package tdtu.movieapp.app.data.model.Treding

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @ColumnInfo("movie_poster")
    @SerializedName("poster_path")
    val poster_path: String,
    @ColumnInfo("movie_title")
    @SerializedName("title")
    val title: String,
    @ColumnInfo("movie_overview")
    @SerializedName("overview")
    val overview: String,
    @ColumnInfo("movie_tagline")
    @SerializedName("tagline")
    val tagline: String,
    @ColumnInfo("movie_director")
    @SerializedName("director_id")
    val director_id: String,
    @ColumnInfo("movie_year")
    @SerializedName("yearProduce")
    val yearProduce: Int
)
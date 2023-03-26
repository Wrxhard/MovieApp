package tdtu.movieapp.app.data.model.Movies

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
    @SerializedName("director_id")
    val director_id: String,
    @SerializedName("yearProduce")
    val yearProduce: Int
)
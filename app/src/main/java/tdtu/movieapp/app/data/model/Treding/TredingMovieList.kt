package tdtu.movieapp.app.data.model.Treding

import com.google.gson.annotations.SerializedName

data class TredingMovieList(
    @SerializedName("results")
    val tredingMovies: List<TredingMovie>,
)
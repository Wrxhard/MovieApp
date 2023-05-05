package tdtu.movieapp.app.data.model.Movies

import com.google.gson.annotations.SerializedName

data class UserAuth(
    @SerializedName("title")
    val title: String,
    @SerializedName("id")
    val id:String,
    @SerializedName("username")
    val name:String,
    @SerializedName("status")
    val status:Boolean
)

package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Result(
        @SerializedName("gender") val gender: String,
        @SerializedName("name") val name: Name,
        @SerializedName("location") val location: Location,
        @SerializedName("email") val email: String,
        @SerializedName("login") val login: Login,
        @SerializedName("dob") val dob: String,
        @SerializedName("registered") val registered: String,
        @SerializedName("phone") val phone: String,
        @SerializedName("cell") val cell: String,
        @SerializedName("id") val id: Id,
        @SerializedName("picture") val picture: Picture,
        @SerializedName("nat") val nat: String
)
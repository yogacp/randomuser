package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Result(
        @SerializedName("gender") var gender: String,
        @SerializedName("name") var name: Name,
        @SerializedName("location") var location: Location,
        @SerializedName("email") var email: String,
        @SerializedName("login") var login: Login,
        @SerializedName("dob") var dob: String,
        @SerializedName("registered") var registered: String,
        @SerializedName("phone") var phone: String,
        @SerializedName("cell") var cell: String,
        @SerializedName("id") var id: Id,
        @SerializedName("picture") var picture: Picture,
        @SerializedName("nat") var nat: String
)
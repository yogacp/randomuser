package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Login(
        @SerializedName("username") var username: String,
        @SerializedName("password") var password: String,
        @SerializedName("salt") var salt: String,
        @SerializedName("md5") var md5: String,
        @SerializedName("sha1") var sha1: String,
        @SerializedName("sha256") var sha256: String
)
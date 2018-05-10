package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.SerializedName



data class Info(
        @SerializedName("seed") var seed: String,
        @SerializedName("results") var results: Int,
        @SerializedName("page") var page: Int,
        @SerializedName("version") var version: String
)
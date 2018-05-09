package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Name(
        @SerializedName("title") val title: String,
        @SerializedName("first") val first: String,
        @SerializedName("last") val last: String
)
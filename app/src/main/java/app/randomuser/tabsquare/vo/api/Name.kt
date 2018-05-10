package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Name(
        @SerializedName("title") var title: String,
        @SerializedName("first") var first: String,
        @SerializedName("last") var last: String
)
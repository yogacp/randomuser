package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.SerializedName

data class Id(
        @SerializedName("name") var name: String,
        @SerializedName("value") var value: String
)
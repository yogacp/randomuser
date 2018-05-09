package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.SerializedName

data class Id(
        @SerializedName("name") val name: String,
        @SerializedName("value") val value: String
)
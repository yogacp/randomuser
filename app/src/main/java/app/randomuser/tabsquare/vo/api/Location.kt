package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Location(
        @SerializedName("street") val street: String,
        @SerializedName("city") val city: String,
        @SerializedName("state") val state: String,
        @SerializedName("postcode") val postcode: Int
)
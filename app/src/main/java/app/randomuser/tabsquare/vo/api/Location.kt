package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Location(
        @SerializedName("street") var street: String,
        @SerializedName("city") var city: String,
        @SerializedName("state") var state: String,
        @SerializedName("postcode") var postcode: Int
)
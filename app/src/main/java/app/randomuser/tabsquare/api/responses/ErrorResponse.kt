package app.randomuser.tabsquare.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
        @SerializedName("error")
        @Expose
        var error: String
)
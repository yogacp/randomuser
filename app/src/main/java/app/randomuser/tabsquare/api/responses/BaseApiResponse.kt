package app.randomuser.tabsquare.api.responses

import app.randomuser.tabsquare.vo.api.Info
import com.google.gson.annotations.SerializedName

data class BaseApiResponse<T>(
        @SerializedName("info") val info: Info,
        @SerializedName("results") val result: List<T>
)
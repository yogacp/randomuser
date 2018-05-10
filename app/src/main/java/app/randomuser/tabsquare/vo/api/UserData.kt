package app.randomuser.tabsquare.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserData(
        @SerializedName("info") var info: Info,
        @SerializedName("results") var result: List<Result>
)
package app.randomuser.tabsquare.api

import app.randomuser.tabsquare.api.responses.BaseApiResponse
import app.randomuser.tabsquare.vo.api.Result
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServices {

    @GET("/api/?nat=US")
    fun getUsers(@Query("page") page :String, @Query("results") results :String) : Flowable<BaseApiResponse<Result>>

}
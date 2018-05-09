package app.randomuser.tabsquare.api

import app.randomuser.tabsquare.api.responses.BaseApiResponse
import app.randomuser.tabsquare.vo.api.Result
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkServices {

    @GET("/api/?page={page}&results={resultCount}&nat=US")
    fun getUsers(@Path("page") page :String, @Path("resultCount") resultCount :String) : Flowable<BaseApiResponse<Result>>

}
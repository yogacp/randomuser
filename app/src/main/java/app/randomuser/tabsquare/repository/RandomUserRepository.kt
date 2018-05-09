package app.randomuser.tabsquare.repository

import app.randomuser.tabsquare.api.NetworkServices
import app.randomuser.tabsquare.api.responses.BaseApiResponse
import app.randomuser.tabsquare.vo.api.Result
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RandomUserRepository @Inject constructor(var mNetworkServices: NetworkServices) {

    /**
     * Get Users
     */
    fun getUsers(page: String, resultCount: String): Flowable<BaseApiResponse<Result>> {
        return mNetworkServices.getUsers(page, resultCount)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
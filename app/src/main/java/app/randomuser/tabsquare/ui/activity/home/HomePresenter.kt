package app.randomuser.tabsquare.ui.activity.home

import android.content.Context
import app.randomuser.tabsquare.api.responses.BaseApiResponse
import app.randomuser.tabsquare.helper.Helper
import app.randomuser.tabsquare.model.UserDataModel
import app.randomuser.tabsquare.model.UserDetailDataModel
import app.randomuser.tabsquare.repository.RandomUserRepository
import app.randomuser.tabsquare.ui.activity.home.HomeActivity.Companion.REFRESH_DATA
import app.randomuser.tabsquare.vo.api.Result
import app.randomuser.tabsquare.vo.api.UserData
import app.randomuser.tabsquare.vo.db.UserDetailData
import app.randomuser.tabsquare.vo.db.UsersData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class HomePresenter @Inject constructor(
        val mHelper : Helper,
        val mContext : Context,
        val mGson : Gson,
        val mUserDataModel: UserDataModel,
        val mUserDetailDataModel: UserDetailDataModel,
        val mRepository: RandomUserRepository
): HomeContract.UserActionListener {

    lateinit var mView: HomeContract.View
    var mDisposable = CompositeDisposable()

    override fun checkUserData(reqPage: String, count: String, state: String) {
        mView.showProgressBar()
        mView.hideEmptyResult()
        mView.hideErrorResult()
        mView.hideOfflineView()

        if(mHelper.isNetworkConnected(mContext)) {
            getUsersList(reqPage, count, state)
        } else {
            loadFromDB(reqPage, count, state)
        }
    }

    override fun loadFromDB(reqPage: String, count: String, state: String) {
        mUserDataModel.getUserData(reqPage).let {
            if (it != null) {
                mView.hideProgressBar()
                var mListResult : List<Result>? = null
                for (userData in it) {
                    val resultData = mGson.fromJson(userData.data, UserData::class.java)
                    mListResult = resultData.result
                }

                mView.setUserList(mListResult!!)
                if(state.equals(REFRESH_DATA)) {
                    mView.setAdapter()
                }
            } else {
                mView.hideProgressBar()
                mView.hideEmptyResult()
                if(state.equals(REFRESH_DATA)) {
                    mView.showErrorResult("User is offline and there is no cached data")
                } else {
                    mView.showOfflineView()
                }
            }
        }
    }

    override fun getUsersList(reqPage: String, count: String, state: String) {
        mDisposable.add(mRepository.getUsers(reqPage, count)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<BaseApiResponse<Result>>(){
                    override fun onNext(response: BaseApiResponse<Result>?) {
                        mView.hideProgressBar()

                        if(response != null && response.result.size > 0) {
                            val userData = UsersData()
                            userData.apply {
                                data = mGson.toJson(response)
                                page = reqPage
                                lastUpdated = System.currentTimeMillis()
                            }
                            mUserDataModel.delete(reqPage)
                            mUserDataModel.saveUserData(userData)
                            mView.setUserList(response.result)
                            if(state.equals(REFRESH_DATA)) {
                                mView.setAdapter()
                            }

                        } else {
                            mView.showEmptyResult()
                        }
                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable?) {
                        mView.hideProgressBar()
                        mView.hideEmptyResult()
                        if(state.equals(REFRESH_DATA)) {
                            mView.showErrorResult("Error while getting user list")
                        } else {
                            mView.showOfflineView()
                        }
                    }

                }))

    }

    override fun saveUserDetailData(userHash: String, result: Result) {
        mUserDetailDataModel.delete(userHash)
        val userDetailData = UserDetailData()
        userDetailData.apply {
            md5 = userHash
            data = mGson.toJson(result)
            lastUpdated = System.currentTimeMillis()
        }
        mUserDetailDataModel.saveUserData(userDetailData)
    }
}
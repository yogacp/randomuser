package app.randomuser.tabsquare.ui.activity.home

import android.content.Context
import app.randomuser.tabsquare.api.responses.BaseApiResponse
import app.randomuser.tabsquare.helper.Helper
import app.randomuser.tabsquare.model.UserDataModel
import app.randomuser.tabsquare.repository.RandomUserRepository
import app.randomuser.tabsquare.vo.api.Result
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
        val mRepository: RandomUserRepository
): HomeContract.UserActionListener {

    lateinit var mView: HomeContract.View
    var mDisposable = CompositeDisposable()

    override fun checkUserData(reqPage: String, count: String) {
        mView.showProgressBar()
        mView.hideEmptyResult()
        mView.hideErrorResult()

        if(mHelper.isNetworkConnected(mContext)) {
            getUsersList(reqPage, count)
        } else {
            loadFromDB(reqPage, count)
        }
    }

    override fun loadFromDB(reqPage: String, count: String) {
        mUserDataModel.getUserData(reqPage).let {
            if (it != null) {
                val mListResult = ArrayList<Result>()
                for (userData in it) {
                    mListResult.add(mGson.fromJson(userData.data, Result::class.java))
                }
                mView.setAdapter(mListResult)
                mView.hideProgressBar()
            } else {
                mView.hideProgressBar()
                mView.hideEmptyResult()
                mView.showErrorResult("User is offline and there is no cached data")
            }
        }
    }

    override fun getUsersList(reqPage: String, count: String) {
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
                            mUserDataModel.saveUserData(userData)
                            mView.setAdapter(response.result)
                        } else {
                            mView.showEmptyResult()
                        }
                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable?) {
                        mView.hideProgressBar()
                        mView.hideEmptyResult()
                        mView.showErrorResult("Error while getting user list")
                    }

                }))

    }
}
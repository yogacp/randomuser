package app.randomuser.tabsquare.ui.activity.detailuser

import android.content.Context
import app.randomuser.tabsquare.helper.Helper
import app.randomuser.tabsquare.model.UserDetailDataModel
import app.randomuser.tabsquare.vo.api.Result
import app.randomuser.tabsquare.vo.api.UserData
import app.randomuser.tabsquare.vo.db.UserDetailData
import com.google.gson.Gson
import javax.inject.Inject

class DetailUserPresenter @Inject constructor(
        val mGson : Gson,
        val mUserDetailDataModel: UserDetailDataModel
): DetailUserContract.UserActionListener {

    lateinit var mView: DetailUserContract.View

    override fun getDetailUser(userHash: String) {
        mUserDetailDataModel.getUserData(userHash).let {
            if (it != null) {
                mView.setupData(mGson.fromJson(it.first().data, Result::class.java))
            }
        }
    }
}
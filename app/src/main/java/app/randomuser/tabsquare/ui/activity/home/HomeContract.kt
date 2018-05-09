package app.randomuser.tabsquare.ui.activity.home

import android.widget.ImageView
import app.randomuser.tabsquare.vo.api.Result

interface HomeContract {
    interface View {
        fun loadUserList()
        fun setAdapter(products: List<Result>)
        fun loadImageToImageView(mImagesUrl: String, imgView: ImageView)
        fun setupUI()
        fun showProgressBar()
        fun hideProgressBar()
        fun showEmptyResult()
        fun hideEmptyResult()
        fun showErrorResult(message: String)
        fun hideErrorResult()
        fun dpToPx(dp: Int): Int
        fun showExitPopup()
        fun exitApp()
    }

    interface UserActionListener {
        fun checkUserData(reqPage: String, count: String)
        fun getUsersList(reqPage: String, count: String)
        fun loadFromDB(reqPage: String, count: String)
    }
}
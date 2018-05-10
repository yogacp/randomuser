package app.randomuser.tabsquare.ui.activity.detailuser

import android.widget.ImageView
import app.randomuser.tabsquare.vo.api.Result

interface DetailUserContract {
    interface View {
        fun initalizeData()
        fun setupUI()
        fun setupData(result: Result?)
        fun loadImageToImageView(mImagesUrl: String, imgView: ImageView)
    }

    interface UserActionListener {
        fun getDetailUser(userHash: String)
    }
}
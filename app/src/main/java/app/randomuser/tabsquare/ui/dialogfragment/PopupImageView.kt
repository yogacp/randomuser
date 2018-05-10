package app.randomuser.tabsquare.ui.dialogfragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.Window
import android.widget.ImageView
import app.randomuser.tabsquare.R
import app.randomuser.tabsquare.ui.common.BaseDialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.df_imageview_user.*

class PopupImageView : BaseDialogFragment() {

    var mImageString = ""

    companion object {
        val TAG_POPUP_IMAGEVIEW_USER = "popup_imageview_user"
        val TAG_IMAGEVIEW_USER_SRC = "src_imageview_user"
    }

    override fun getLayoutId(): Int {
        return R.layout.df_imageview_user
    }

    override fun onLoadFragment(saveInstance: Bundle?) {
        //Disable dismiss outside dialog
        dialog.setCanceledOnTouchOutside(false)

        //Set dialog with no toolbar
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        //Set dialog with no frame
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)

        val args = arguments
        args?.let {
            mImageString = it.getString(TAG_IMAGEVIEW_USER_SRC)
        }

        setupUIListener()
        loadImageToImageView(mImageString, imgUserView)
    }

    fun setupUIListener() {
        btnCloseMenu.setOnClickListener {
            dismiss()
        }
    }

    fun loadImageToImageView(mImagesUrl: String, imgCategory: ImageView) {
        Picasso.get()
                .load(Uri.parse(mImagesUrl))
                .fit()
                .centerInside()
                .into(imgCategory)
    }
}
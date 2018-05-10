package app.randomuser.tabsquare.ui.common.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import app.randomuser.tabsquare.ui.activity.detailuser.DetailUserActivity
import app.randomuser.tabsquare.ui.activity.detailuser.DetailUserActivity.Companion.TAG_USER_HASH
import app.randomuser.tabsquare.ui.activity.home.HomeActivity
import app.randomuser.tabsquare.ui.dialogfragment.PopupImageView
import app.randomuser.tabsquare.ui.dialogfragment.PopupImageView.Companion.TAG_IMAGEVIEW_USER_SRC
import app.randomuser.tabsquare.ui.dialogfragment.PopupImageView.Companion.TAG_POPUP_IMAGEVIEW_USER
import javax.inject.Inject

class ActivityNavigation @Inject constructor(val activity: AppCompatActivity) {

    /**
     * Intent to Home Page
     * */
    fun navigateToHomePage() {
        activity.startActivity(newIntent(activity, HomeActivity::class.java))
    }

    /**
     * Intent to Detail Page
     * */
    fun navigateToDetailPage(userHash: String) {
        val detailActivity = newIntent(activity, DetailUserActivity::class.java)
        detailActivity.apply {
            putExtra(TAG_USER_HASH, userHash)
        }
        activity.startActivity(detailActivity)
    }

    /**
     * Navigate to Popup Menu Dialog Fragment
     * */
    fun navigateToPopupImageUser(itemImage: String) {

        val args = Bundle().apply {
            putString(TAG_IMAGEVIEW_USER_SRC, itemImage)
        }

        val sFragment = PopupImageView().apply {
            arguments = args
        }

        loadDialogFragment(
                sFragment,
                PopupImageView.TAG_POPUP_IMAGEVIEW_USER
        )
    }

    /**
     * Load dialog fragment
     * Handling all load dialog fragment navigation
     * */

    fun loadDialogFragment(mDialogFragment: DialogFragment, mTagFragment: String) {
        mDialogFragment.show(activity.supportFragmentManager, mTagFragment)
    }

    /**
     * Intent Common Function
     * Handling new intent
     * */

    fun <T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }

    fun newIntentUri(label: String, uri: Uri): Intent {
        return Intent(label, uri)
    }
}
package app.randomuser.tabsquare.ui.common.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import app.randomuser.tabsquare.ui.activity.home.HomeActivity
import javax.inject.Inject

class ActivityNavigation @Inject constructor(val activity: AppCompatActivity) {

    /**
     * Intent to Login Page
     * */
    fun navigateToHomePage() {
        activity.startActivity(newIntent(activity, HomeActivity::class.java))
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
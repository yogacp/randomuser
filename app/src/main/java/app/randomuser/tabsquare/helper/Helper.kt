package app.randomuser.tabsquare.helper

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AlertDialog
import app.randomuser.tabsquare.R

class Helper (val context: Context) {
    /**
     * Checking internet connection
     * @Param context
     * @Return boolean
     */

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    /**
     * Show Popup Dialog
     */
    fun showPopupDialog(activity: Activity,
                        title: String,
                        msg: String,
                        dialogSource: String,
                        btnPositive: String,
                        btnNegative: String,
                        callbackDialog: CallbackDialog) {

        val alertDialog = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AlertDialog.Builder(activity, R.style.RandomUserDialog).create()
        } else {
            AlertDialog.Builder(activity).create()
        }

        alertDialog?.setTitle(title)
        alertDialog?.setMessage(msg)
        alertDialog!!.setCancelable(false)

        if (dialogSource == "1") {
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, btnPositive, { _, _ ->
                callbackDialog.onButtonPositiveClicked()
            })
        } else {
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, btnPositive, { _, _ ->
                callbackDialog.onButtonPositiveClicked()
            })

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, btnNegative, { _, _ ->
                callbackDialog.onButtonNegativeClicked()
            })
        }

        alertDialog.show()
    }

    /**
     * Common callback for alert dialog
     * */

    interface CallbackDialog {
        fun onButtonPositiveClicked()
        fun onButtonNegativeClicked()
    }
}
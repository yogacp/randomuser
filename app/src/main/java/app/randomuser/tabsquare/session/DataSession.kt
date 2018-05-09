package app.randomuser.tabsquare.session

import android.content.Context
import app.randomuser.tabsquare.RandomUserApp

class DataSession {

    var pref = Pref()

    inner class Pref internal constructor() : BaseSharedPreferences() {
        init {
            check()
        }

        override fun _getApplicationContext(): Context {
            return RandomUserApp.instance
        }

        override fun _getUserProfileName(): String {
            return "user_session"
        }
    }
}
package app.randomuser.tabsquare.ui.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.randomuser.tabsquare.helper.Helper
import app.randomuser.tabsquare.ui.common.navigation.ActivityNavigation
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var mHelper: Helper

    @Inject
    lateinit var mActivityNavigation: ActivityNavigation

    var mDisposables = CompositeDisposable()

    /**
     * This function replace onCreate as main function run in activity
     * Auto Dependency Injection
     * @param Bundle
     * */

    abstract fun onActivityReady(savedInstanceState: Bundle?)

    /**
     * Getting Layout ID from activity
     * */

    abstract fun getLayoutId () : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        //Auto DI
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        onActivityReady(savedInstanceState)
    }

}
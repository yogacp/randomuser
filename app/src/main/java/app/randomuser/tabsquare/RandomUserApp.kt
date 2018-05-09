package app.randomuser.tabsquare

import android.app.Activity
import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.randomuser.tabsquare.di.component.AppComponent
import app.randomuser.tabsquare.di.component.DaggerAppComponent
import app.randomuser.tabsquare.di.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class RandomUserApp : Application(), HasActivityInjector {

    companion object {
        @JvmStatic lateinit var instance: RandomUserApp
        @JvmStatic lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        // Set Instance
        instance = this

        // Set App Component
        appComponent = createComponent()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return mActivityInjector
    }

    /**
     * Initialize Dependency Injection With Dagger 2
     * Level DI Application
     * */
    fun createComponent(): AppComponent {
        val appComponent = DaggerAppComponent.builder()
                .application(this)
                .networkModule(NetworkModule(this))
                .build()
        return appComponent
    }

}

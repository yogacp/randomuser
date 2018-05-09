package app.randomuser.tabsquare.di.module

import android.app.Application
import android.content.Context
import app.randomuser.tabsquare.helper.Helper
import app.randomuser.tabsquare.session.DataSession
import app.randomuser.tabsquare.utils.RxBus
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun gson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    internal fun provideHelper(context: Context): Helper {
        return Helper(context)
    }

    @Provides @Singleton
    internal fun rxBus(): RxBus {
        return RxBus()
    }

    @Provides @Singleton
    internal fun provideDataSession(): DataSession {
        return DataSession()
    }

}
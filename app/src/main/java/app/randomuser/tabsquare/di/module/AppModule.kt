package app.randomuser.tabsquare.di.module

import android.app.Application
import android.content.Context
import app.randomuser.tabsquare.RandomUserApp
import app.randomuser.tabsquare.helper.Helper
import app.randomuser.tabsquare.model.UserDataModel
import app.randomuser.tabsquare.model.UserDetailDataModel
import app.randomuser.tabsquare.session.DataSession
import app.randomuser.tabsquare.utils.AppConstants
import app.randomuser.tabsquare.utils.RxBus
import app.randomuser.tabsquare.vo.db.DaoMaster
import app.randomuser.tabsquare.vo.db.DaoSession
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

    @Provides
    @Singleton
    internal fun provideDaoSession(): DaoSession {
        val dbName = AppConstants.database.databaseName
        val devOpenHelper = DaoMaster.DevOpenHelper(RandomUserApp.instance, dbName)
        val db = devOpenHelper.writableDatabase
        val daoMaster = DaoMaster(db)
        return daoMaster.newSession()
    }

    @Provides
    @Singleton
    internal fun provideUserDataModel(daoSession: DaoSession) = UserDataModel(daoSession)

    @Provides
    @Singleton
    internal fun provideUserDetailDataModel(daoSession: DaoSession) = UserDetailDataModel(daoSession)

}
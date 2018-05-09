package app.randomuser.tabsquare.di.module.builder

import app.randomuser.tabsquare.di.scope.ActivityScope
import app.randomuser.tabsquare.ui.activity.home.HomeActivity
import app.randomuser.tabsquare.ui.activity.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(HomeModule::class))
    internal abstract fun bindHomeActivity(): HomeActivity
}
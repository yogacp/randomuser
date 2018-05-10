package app.randomuser.tabsquare.ui.activity.home

import app.randomuser.tabsquare.api.NetworkServices
import app.randomuser.tabsquare.di.scope.ActivityScope
import app.randomuser.tabsquare.repository.RandomUserRepository
import app.randomuser.tabsquare.ui.common.navigation.ActivityNavigation
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @ActivityScope
    internal fun provideNavigation(homeActivity: HomeActivity) : ActivityNavigation {
        return ActivityNavigation(homeActivity)
    }

    @Provides @ActivityScope
    internal fun provideHomeActivity(homeActivity: HomeActivity): HomeContract.View {
        return homeActivity
    }

    @Provides
    @ActivityScope
    internal fun provideRandomUserRepository(mNetworkServices: NetworkServices): RandomUserRepository {
        return RandomUserRepository(mNetworkServices)
    }
}
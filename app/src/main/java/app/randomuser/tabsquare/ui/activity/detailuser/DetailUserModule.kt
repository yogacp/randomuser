package app.randomuser.tabsquare.ui.activity.detailuser

import app.randomuser.tabsquare.di.scope.ActivityScope
import app.randomuser.tabsquare.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class DetailUserModule {
    @Provides
    @ActivityScope
    internal fun provideNavigation(detailUserActivity: DetailUserActivity) : ActivityNavigation {
        return ActivityNavigation(detailUserActivity)
    }

    @Provides @ActivityScope
    internal fun provideDetailUserActivity(detailUserActivity: DetailUserActivity): DetailUserContract.View {
        return detailUserActivity
    }
}
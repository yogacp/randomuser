package app.randomuser.tabsquare.di.component

import android.app.Application
import app.randomuser.tabsquare.RandomUserApp
import app.randomuser.tabsquare.di.module.AppModule
import app.randomuser.tabsquare.di.module.NetworkModule
import app.randomuser.tabsquare.di.module.builder.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                AndroidInjectionModule::class,
                ActivityBuilder::class,
                AppModule::class,
                NetworkModule::class
        )
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: RandomUserApp)
}
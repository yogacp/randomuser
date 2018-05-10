package app.randomuser.tabsquare.di.module.builder

import app.randomuser.tabsquare.ui.dialogfragment.PopupImageView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CommonFragmentBuilder {
    @ContributesAndroidInjector
    internal abstract fun contributePopupImageView(): PopupImageView
}
package com.github.abhrp.simpleton.di.module

import com.github.abhrp.domain.PostExecutionThread
import com.github.abhrp.simpleton.UIThread
import com.github.abhrp.simpleton.ui.UserInfoActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesUserActivity(): UserInfoActivity

}
package com.github.abhrp.simpleton.di

import android.app.Application
import com.github.abhrp.simpleton.SimpletonApp
import com.github.abhrp.simpleton.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (ApplicationModule::class),
    (UiModule::class),
    (PresentationModule::class),
    (DataModule::class),
    (CacheModule::class),
    (RemoteModule::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: SimpletonApp)
}
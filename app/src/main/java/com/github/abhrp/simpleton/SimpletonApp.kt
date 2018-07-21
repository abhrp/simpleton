package com.github.abhrp.simpleton

import android.app.Activity
import android.app.Application
import com.github.abhrp.simpleton.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class SimpletonApp : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
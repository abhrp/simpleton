package com.github.abhrp.simpleton

import android.app.Application
import timber.log.Timber

class SimpletonApp: Application(){

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
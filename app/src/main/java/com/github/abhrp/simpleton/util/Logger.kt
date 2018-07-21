package com.github.abhrp.simpleton.util

import android.text.TextUtils
import com.github.abhrp.simpleton.BuildConfig
import timber.log.Timber
import javax.inject.Inject

class Logger @Inject constructor() {

    private val TAG = Logger::class.simpleName

    fun i(tag: String?, message: String) {
        if (BuildConfig.DEBUG) {
            val t = if (TextUtils.isEmpty(tag)) TAG else tag
            Timber.tag(t).i(message)
        }
    }

    fun e(tag: String?, throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            val t = if (TextUtils.isEmpty(tag)) TAG else tag
            Timber.tag(t).e(throwable)
        }
    }
}
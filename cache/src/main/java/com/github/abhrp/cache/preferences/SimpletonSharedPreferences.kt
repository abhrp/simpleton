package com.github.abhrp.cache.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SimpletonSharedPreferences @Inject constructor(private val context: Context) {

    companion object {
        private const val PREF_NAME = "com.github.abhrp.cache.preferences"

        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val simpletonPref: SharedPreferences

    init {
        simpletonPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    var lastCacheTime: Long
        get() = simpletonPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = simpletonPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()
}
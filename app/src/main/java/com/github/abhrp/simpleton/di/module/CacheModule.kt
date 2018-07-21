package com.github.abhrp.simpleton.di.module

import android.app.Application
import com.github.abhrp.cache.SimpletonDatabase
import com.github.abhrp.cache.UserCacheImpl
import com.github.abhrp.data.repository.UserCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): SimpletonDatabase {
            return SimpletonDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindsUserCache(userCacheImpl: UserCacheImpl): UserCache
}
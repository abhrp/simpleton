package com.github.abhrp.simpleton.di.module

import com.github.abhrp.data.repository.UserRemote
import com.github.abhrp.remote.UserRemoteImpl
import com.github.abhrp.remote.service.UserApiService
import com.github.abhrp.remote.service.UserApiServiceFactory
import com.github.abhrp.simpleton.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesUserApiService(): UserApiService {
            return UserApiServiceFactory.getUserApiService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindsUserRemote(userRemoteImpl: UserRemoteImpl): UserRemote
}
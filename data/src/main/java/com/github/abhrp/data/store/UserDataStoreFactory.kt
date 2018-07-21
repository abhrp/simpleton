package com.github.abhrp.data.store

import com.github.abhrp.data.repository.UserDataStore
import javax.inject.Inject

class UserDataStoreFactory @Inject constructor(private val userCacheDataStore: UserCacheDataStore,
                                               private val userRemoteDataStore: UserRemoteDataStore) {
    fun getUserDataStore(isUserCached: Boolean, isCacheExpired: Boolean, forceRemote: Boolean): UserDataStore {
        if (forceRemote) {
            return userRemoteDataStore
        }
        return if (isUserCached && !isCacheExpired) userCacheDataStore else userRemoteDataStore
    }

    fun getUserCacheDataStore(): UserCacheDataStore {
        return userCacheDataStore
    }

    fun getUserRemoteDataStore(): UserRemoteDataStore {
        return userRemoteDataStore
    }
}
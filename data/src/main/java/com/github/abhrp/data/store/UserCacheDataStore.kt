package com.github.abhrp.data.store

import com.github.abhrp.data.model.UserEntity
import com.github.abhrp.data.repository.UserCache
import com.github.abhrp.data.repository.UserDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserCacheDataStore @Inject constructor(private val userCache: UserCache): UserDataStore {
    override fun getUser(): Observable<UserEntity> {
        return userCache.getUser()
    }

    override fun deleteUser(id: String): Completable {
        return userCache.deleteUser(id)
    }

    override fun saveUser(userEntity: UserEntity): Completable {
        return userCache.saveUser(userEntity).andThen(userCache.setLastCacheTime(System.currentTimeMillis()))
    }
}
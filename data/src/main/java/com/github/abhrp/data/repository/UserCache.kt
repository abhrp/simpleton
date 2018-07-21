package com.github.abhrp.data.repository

import com.github.abhrp.data.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface UserCache {
    fun deleteUser(id: String): Completable
    fun saveUser(userEntity: UserEntity): Completable
    fun getUser(): Observable<UserEntity>
    fun isUserCached(): Single<Boolean>
    fun setLastCacheTime(cacheTime: Long): Completable
    fun isCacheExpired(): Single<Boolean>
}
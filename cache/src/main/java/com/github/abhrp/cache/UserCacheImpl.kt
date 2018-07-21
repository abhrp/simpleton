package com.github.abhrp.cache

import com.github.abhrp.cache.mapper.UserEntityMapper
import com.github.abhrp.cache.preferences.SimpletonSharedPreferences
import com.github.abhrp.data.model.UserEntity
import com.github.abhrp.data.repository.UserCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UserCacheImpl @Inject constructor(private val simpletonDatabase: SimpletonDatabase,
                                        private val userEntityMapper: UserEntityMapper,
                                        private val simpletonSharedPreferences: SimpletonSharedPreferences) : UserCache {
    override fun deleteUser(id: String): Completable {
        return Completable.defer {
            simpletonDatabase.getUserDao().deleteUser(id)
            Completable.complete()
        }
    }

    override fun saveUser(userEntity: UserEntity): Completable {
        return Completable.defer {
            simpletonDatabase.getUserDao().saveUser(userEntityMapper.mapFromEntity(userEntity))
            Completable.complete()
        }
    }

    override fun getUser(): Observable<UserEntity> {
        return simpletonDatabase.getUserDao().getAllUsers().toObservable().map {
            users ->
            val user = users[0]
            userEntityMapper.mapToEntity(user)
        }
    }

    override fun isUserCached(): Single<Boolean> {
        return simpletonDatabase.getUserDao().getAllUsers().toObservable().isEmpty.map { !it }
    }

    override fun setLastCacheTime(cacheTime: Long): Completable {
        return Completable.defer {
            simpletonSharedPreferences.lastCacheTime = cacheTime
            Completable.complete()
        }
    }


    override fun isCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        val lastCacheTime = simpletonSharedPreferences.lastCacheTime
        val isExpired = if (lastCacheTime == 0L) true else currentTime - lastCacheTime > expirationTime
        return Single.just(isExpired)
    }
}
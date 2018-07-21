package com.github.abhrp.data

import com.github.abhrp.data.mapper.UserMapper
import com.github.abhrp.data.repository.UserCache
import com.github.abhrp.data.store.UserDataStoreFactory
import com.github.abhrp.domain.model.User
import com.github.abhrp.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class UserDataRepository @Inject constructor(private val userCache: UserCache,
                                             private val userDataStoreFactory: UserDataStoreFactory,
                                             private val userMapper: UserMapper): UserRepository {

    override fun getUserInfo(): Observable<User> {
        return Observable.zip(userCache.isUserCached().toObservable(),
                userCache.isCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { isUserCached, isCacheExpired ->
                    Pair(isUserCached, isCacheExpired)
                })
                .flatMap {
                    userDataStoreFactory.getUserDataStore(it.first, it.second).getUser()
                }
                .flatMap {
                    userDataStoreFactory.getUserCacheDataStore().saveUser(it).andThen(Observable.just(it))
                }
                .map {
                    userMapper.mapToData(it)
                }
    }

    override fun deleteUser(id: String): Completable {
        return userDataStoreFactory.getUserRemoteDataStore().deleteUser(id)
                .andThen(userDataStoreFactory.getUserCacheDataStore().deleteUser(id))
                .andThen(userCache.setLastCacheTime(0))
    }
}
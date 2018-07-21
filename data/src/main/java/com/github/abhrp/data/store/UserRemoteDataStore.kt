package com.github.abhrp.data.store

import com.github.abhrp.data.model.UserEntity
import com.github.abhrp.data.repository.UserDataStore
import com.github.abhrp.data.repository.UserRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserRemoteDataStore @Inject constructor(private val userRemote: UserRemote): UserDataStore {
    override fun getUser(): Observable<UserEntity> {
        return userRemote.getUser()
    }

    override fun deleteUser(id: String): Completable {
        return userRemote.deleteUser(id)
    }

    override fun saveUser(userEntity: UserEntity): Completable {
        throw UnsupportedOperationException("Cannot save on remote")
    }
}
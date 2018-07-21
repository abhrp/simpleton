package com.github.abhrp.data.repository

import com.github.abhrp.data.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface UserDataStore {
    fun getUser(): Observable<UserEntity>
    fun deleteUser(id: String): Completable
    fun saveUser(userEntity: UserEntity): Completable
}
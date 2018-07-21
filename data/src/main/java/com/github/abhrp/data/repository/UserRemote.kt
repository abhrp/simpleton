package com.github.abhrp.data.repository

import com.github.abhrp.data.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRemote {
    fun getUser(): Observable<UserEntity>
    fun deleteUser(id: String): Completable
}
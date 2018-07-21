package com.github.abhrp.domain.repository

import com.github.abhrp.domain.model.User
import io.reactivex.Completable
import io.reactivex.Observable


interface UserRepository {
    fun getUserInfo(): Observable<User>
    fun deleteUser(id: String): Completable
}
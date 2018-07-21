package com.github.abhrp.remote.service

import com.github.abhrp.remote.constants.NetworkConstants
import com.github.abhrp.remote.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET(NetworkConstants.API_GET_USER)
    fun getUser(): Observable<User>

    @DELETE(NetworkConstants.API_DELETE_USER)
    fun deleteUser(@Path("id") id: String): Completable
}
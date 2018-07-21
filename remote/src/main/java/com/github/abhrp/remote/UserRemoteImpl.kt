package com.github.abhrp.remote

import com.github.abhrp.data.model.UserEntity
import com.github.abhrp.data.repository.UserRemote
import com.github.abhrp.remote.mapper.UserResponseMapper
import com.github.abhrp.remote.service.UserApiService
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserRemoteImpl @Inject constructor(private val userApiService: UserApiService,
                                         private val userResponseMapper: UserResponseMapper): UserRemote {
    override fun getUser(): Observable<UserEntity> {
       return userApiService.getUser().map { response ->
            userResponseMapper.mapToEntity(response)
        }
    }

    override fun deleteUser(id: String): Completable {
        return userApiService.deleteUser(id)
    }
}
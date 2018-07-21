package com.github.abhrp.remote.mapper

import com.github.abhrp.data.model.UserEntity
import com.github.abhrp.remote.model.User
import javax.inject.Inject

class UserResponseMapper @Inject constructor(): Mapper<User, UserEntity> {
    override fun mapToEntity(model: User): UserEntity {
        return UserEntity(model.id, model.firstName, model.lastName, model.phoneNumber, model.email, model.profilePicture)
    }
}
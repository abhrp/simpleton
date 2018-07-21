package com.github.abhrp.data.mapper

import com.github.abhrp.data.model.UserEntity
import com.github.abhrp.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor(): Mapper<UserEntity, User> {
    override fun mapToEntity(data: User): UserEntity {
        return UserEntity(data.id, data.firstName, data.lastName, data.phoneNumber, data.email, data.profilePicture)
    }

    override fun mapToData(entity: UserEntity): User {
        return User(entity.id, entity.firstName, entity.lastName, entity.phoneNumber, entity.email, entity.profilePicture)
    }
}
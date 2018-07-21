package com.github.abhrp.cache.mapper

import com.github.abhrp.cache.entity.UserInfoEntity
import com.github.abhrp.data.model.UserEntity
import javax.inject.Inject

class UserEntityMapper @Inject constructor(): Mapper<UserInfoEntity, UserEntity> {
    override fun mapToEntity(cacheModel: UserInfoEntity): UserEntity {
        return UserEntity(cacheModel.id, cacheModel.firstName, cacheModel.lastName, cacheModel.phoneNumber, cacheModel.email, cacheModel.profilePicture)
    }

    override fun mapFromEntity(entity: UserEntity): UserInfoEntity {
        return UserInfoEntity(entity.id, entity.firstName, entity.lastName, entity.phoneNumber, entity.email, entity.profilePicture)
    }
}
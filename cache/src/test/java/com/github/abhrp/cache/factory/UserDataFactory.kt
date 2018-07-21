package com.github.abhrp.cache.factory

import com.github.abhrp.cache.entity.UserInfoEntity
import com.github.abhrp.data.model.UserEntity


object UserDataFactory {
    fun makeCachedUser(): UserInfoEntity {
        return UserInfoEntity(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())
    }

    fun makeUserEntity(): UserEntity {
        return UserEntity(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())
    }

    fun getLastCacheTime(): Long {
        return DataFactory.randomLong()
    }
}
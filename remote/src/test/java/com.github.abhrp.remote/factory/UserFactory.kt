package com.github.abhrp.remote.factory

import com.github.abhrp.data.model.UserEntity
import com.github.abhrp.remote.model.User

object UserFactory {
    fun makeUserEntity(): UserEntity = UserEntity(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())
    fun makeUser(): User = User(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())
}
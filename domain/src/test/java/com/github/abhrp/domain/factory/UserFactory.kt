package com.github.abhrp.domain.factory

import com.github.abhrp.domain.model.User

object UserFactory {
    fun makeUser(): User = User(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())
}
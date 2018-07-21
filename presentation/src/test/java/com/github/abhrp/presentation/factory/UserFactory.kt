package com.github.abhrp.presentation.factory


import com.github.abhrp.domain.model.User
import com.github.abhrp.presentation.model.UserView

object UserFactory {
    fun makeUserView(): UserView {
        return UserView(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())
    }

    fun makeUserDomainModel(): User {
        return User(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString())
    }

}
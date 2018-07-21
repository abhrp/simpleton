package com.github.abhrp.presentation.mapper

import com.github.abhrp.domain.model.User
import com.github.abhrp.presentation.model.UserView
import javax.inject.Inject

class ViewMapper @Inject constructor() : Mapper<User, UserView> {
    override fun mapToModelView(domain: User): UserView {
        return UserView(domain.id, domain.firstName, domain.lastName, domain.phoneNumber, domain.email, domain.profilePicture)
    }
}
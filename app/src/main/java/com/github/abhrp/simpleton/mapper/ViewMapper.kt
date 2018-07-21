package com.github.abhrp.simpleton.mapper

import com.github.abhrp.presentation.model.UserView
import com.github.abhrp.simpleton.model.User
import javax.inject.Inject

class ViewMapper @Inject constructor() : Mapper<UserView, User> {
    override fun mapToModel(viewModel: UserView): User {
        return User(viewModel.id, viewModel.firstName, viewModel.lastName, viewModel.phoneNumber, viewModel.email, viewModel.profilePicture)
    }
}
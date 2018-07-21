package com.github.abhrp.simpleton.di.module

import com.github.abhrp.data.UserDataRepository
import com.github.abhrp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsUserDataRepository(userDataRepository: UserDataRepository): UserRepository
}
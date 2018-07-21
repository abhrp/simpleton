package com.github.abhrp.simpleton.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.abhrp.presentation.viewmodel.ShowUserViewModel
import com.github.abhrp.simpleton.di.ViewModelFactory
import com.github.abhrp.simpleton.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShowUserViewModel::class)
    abstract fun bindsShowUserViewModel(showUserViewModel: ShowUserViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
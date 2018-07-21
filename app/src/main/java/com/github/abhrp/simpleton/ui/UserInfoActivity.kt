package com.github.abhrp.simpleton.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.abhrp.presentation.model.UserView
import com.github.abhrp.presentation.state.Resource
import com.github.abhrp.presentation.state.ResourceState
import com.github.abhrp.presentation.viewmodel.ShowUserViewModel
import com.github.abhrp.simpleton.R
import com.github.abhrp.simpleton.di.ViewModelFactory
import com.github.abhrp.simpleton.mapper.ViewMapper
import com.github.abhrp.simpleton.util.Logger
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_info.*
import javax.inject.Inject

class UserInfoActivity : AppCompatActivity() {

    @Inject
    lateinit var mapper: ViewMapper
    @Inject
    lateinit var viewModel: ShowUserViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_user_info)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ShowUserViewModel::class.java)
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        getUser()
        observeDeleteUser()
    }

    private fun getUser() {
        viewModel.getUser().observe(this, Observer<Resource<UserView>> {
            it?.let {
                handleGetUser(it)
                Logger.i(null, "Data : ${it.data}")
            }
        })
        viewModel.fetchUser()
    }

    private fun handleGetUser(resource: Resource<UserView>) {
        when (resource.status) {
            ResourceState.LOADING -> {
            }
            ResourceState.SUCCESS -> {
            }
            ResourceState.ERROR -> {
            }
        }
    }

    private fun observeDeleteUser() {
        viewModel.deleteUser().observe(this, Observer<Resource<Boolean>> {
            it?.let {
                handleDeleteUser(it)
            }
        })
    }

    private fun handleDeleteUser(resource: Resource<Boolean>) {
        when (resource.status) {
            ResourceState.LOADING -> {
            }
            ResourceState.SUCCESS -> {
            }
            ResourceState.ERROR -> {
            }
        }
    }
}

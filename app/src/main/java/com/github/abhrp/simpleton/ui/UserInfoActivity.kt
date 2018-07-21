package com.github.abhrp.simpleton.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.github.abhrp.presentation.model.UserView
import com.github.abhrp.presentation.state.Resource
import com.github.abhrp.presentation.state.ResourceState
import com.github.abhrp.presentation.viewmodel.ShowUserViewModel
import com.github.abhrp.simpleton.R
import com.github.abhrp.simpleton.di.ViewModelFactory
import com.github.abhrp.simpleton.mapper.ViewMapper
import com.github.abhrp.simpleton.util.PicassoCircleTransform
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.content_user_info.*
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

        setUpActionBar()
        setUpDeleteUserButton()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ShowUserViewModel::class.java)
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.return_app)
    }

    private fun setUpDeleteUserButton() {
        deleteUser.setOnClickListener {
            deleteUser()
        }
    }

    override fun onStart() {
        super.onStart()
        getUser(false)
        observeDeleteUser()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (item.itemId) {
                android.R.id.home -> {
                    onBackPressed()
                }
                R.id.action_refresh -> {
                    refreshFromRemote()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getUser(forceRemote: Boolean) {
        viewModel.getUser().observe(this, Observer<Resource<UserView>> {
            it?.let {
                handleGetUser(it)
            }
        })
        viewModel.fetchUser(forceRemote)
    }

    private fun handleGetUser(resource: Resource<UserView>) {
        when (resource.status) {
            ResourceState.LOADING -> {
                progressBar.visibility = View.VISIBLE
            }
            ResourceState.SUCCESS -> {
                progressBar.visibility = View.GONE

                resource.data?.let {
                    val model = mapper.mapToModel(it)
                    viewModel.setUserId(model.id)
                    viewModel.setUserName("${model.firstName} ${model.lastName}")
                    toolbar.title = viewModel.getUserName()
                    Picasso.get().load(model.profilePicture).transform(PicassoCircleTransform()).into(userImage)
                    userName.text = getString(R.string.name, viewModel.getUserName())
                    userPhone.text = getString(R.string.phone, model.phoneNumber)
                    userEmail.text = getString(R.string.email, model.email)
                }
            }
            ResourceState.ERROR -> {
                progressBar.visibility = View.GONE
                Toast.makeText(this, resource.error, Toast.LENGTH_LONG).show()
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


    private fun deleteUser() {
        viewModel.getUser().removeObservers(this)
        viewModel.deleteUserFromResource()
    }

    private fun handleDeleteUser(resource: Resource<Boolean>) {
        when (resource.status) {
            ResourceState.LOADING -> {
                progressBar.visibility = View.VISIBLE
            }
            ResourceState.SUCCESS -> {
                progressBar.visibility = View.GONE
                Toast.makeText(this, getString(R.string.user_deleted_message, viewModel.getUserName()), Toast.LENGTH_LONG).show()
                viewModel.setUserId(null)
                viewModel.setUserName(null)
                toolbar.title = getString(R.string.return_app)
                userImage.setImageDrawable(resources.getDrawable(R.drawable.ic_user_placeholder))
                userName.text = getString(R.string.name, "")
                userPhone.text = getString(R.string.phone, "")
                userEmail.text = getString(R.string.email, "")
            }
            ResourceState.ERROR -> {
                progressBar.visibility = View.GONE
                Toast.makeText(this, resource.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun refreshFromRemote() {
        if (viewModel.getUser().hasObservers()) {
            viewModel.fetchUser(true)
        } else {
            getUser(true)
        }
    }
}

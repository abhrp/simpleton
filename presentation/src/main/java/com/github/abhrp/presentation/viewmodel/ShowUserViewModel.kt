package com.github.abhrp.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.abhrp.domain.model.User
import com.github.abhrp.domain.usecase.delete.DeleteUser
import com.github.abhrp.domain.usecase.show.ShowUser
import com.github.abhrp.presentation.mapper.ViewMapper
import com.github.abhrp.presentation.model.UserView
import com.github.abhrp.presentation.state.Resource
import com.github.abhrp.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ShowUserViewModel @Inject constructor(private val showUser: ShowUser,
                                            private val deleteUser: DeleteUser,
                                            private val viewMapper: ViewMapper) : ViewModel() {

    private val liveDataForGetUser: MutableLiveData<Resource<UserView>> = MutableLiveData()
    private val liveDataForDeleteUser: MutableLiveData<Resource<Boolean>> = MutableLiveData()

    private var userId: String? = null
    private var userName: String? = null

    private fun getUserId(): String {
        userId?.let {
            return it
        }
        return ""
    }

    fun setUserId(userId: String?) {
        this.userId = userId
    }

    fun setUserName(userName: String?) {
        this.userName = userName
    }

    fun getUserName(): String {
        userName?.let {
            return it
        }
        return ""
    }

    override fun onCleared() {
        showUser.disposeAll()
        super.onCleared()
    }

    fun getUser(): LiveData<Resource<UserView>> {
        return liveDataForGetUser
    }

    fun deleteUser(): LiveData<Resource<Boolean>> {
        return liveDataForDeleteUser
    }

    fun deleteUserFromResource() {
        val id = getUserId()
        if (id.isEmpty()) {
            liveDataForDeleteUser.postValue(Resource(ResourceState.ERROR, null, "User already deleted"))
        } else {
            liveDataForDeleteUser.postValue(Resource(ResourceState.LOADING, null, null))
            return deleteUser.execute(DeleteUserSubscriber(), DeleteUser.Params.forUser(getUserId()))
        }
    }

    fun fetchUser(forceRemote: Boolean) {
        liveDataForGetUser.postValue(Resource(ResourceState.LOADING, null, null))
        return showUser.execute(UserSubscriber(), ShowUser.Params.fromSource(forceRemote))
    }

    inner class UserSubscriber : DisposableObserver<User>() {
        override fun onComplete() {

        }

        override fun onNext(t: User) {
            liveDataForGetUser.postValue(Resource(ResourceState.SUCCESS, viewMapper.mapToModelView(t), null))
        }

        override fun onError(e: Throwable) {
            liveDataForGetUser.postValue(Resource(ResourceState.ERROR, liveDataForGetUser.value?.data, e.localizedMessage))
        }
    }

    inner class DeleteUserSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveDataForDeleteUser.postValue(Resource(ResourceState.SUCCESS, true, null))
        }

        override fun onError(e: Throwable) {
            liveDataForDeleteUser.postValue(Resource(ResourceState.ERROR, false, e.localizedMessage))
        }
    }
}
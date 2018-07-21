package com.github.abhrp.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.abhrp.domain.model.User
import com.github.abhrp.domain.usecase.delete.DeleteUser
import com.github.abhrp.domain.usecase.show.ShowUser
import com.github.abhrp.presentation.factory.UserFactory
import com.github.abhrp.presentation.mapper.ViewMapper
import com.github.abhrp.presentation.state.ResourceState
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Captor
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
class ShowUserviewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val showUser = mock<ShowUser>()
    private val deleteUser = mock<DeleteUser>()
    private val mapper = mock<ViewMapper>()
    
    private val viewModel = ShowUserViewModel(showUser, deleteUser, mapper)

    @Captor
    private val captor = argumentCaptor<DisposableObserver<User>>()

    @Test
    fun fetchUserExecutesUseCase() {
        viewModel.fetchUser(false)
        verify(showUser, times(1)).execute(any(), eq(ShowUser.Params.fromSource(false)))
    }

    @Test
    fun fetchUserReturnsSuccess() {
        val user = UserFactory.makeUserDomainModel()
        viewModel.fetchUser(false)
        verify(showUser).execute(captor.capture(), eq(ShowUser.Params.fromSource(false)))
        captor.firstValue.onNext(user)
        assertEquals(ResourceState.SUCCESS, viewModel.getUser().value?.status)
    }

    @Test
    fun fetchUserReturnsData() {
        val user = UserFactory.makeUserDomainModel()
        val userView = mapper.mapToModelView(user)
        viewModel.fetchUser(false)
        verify(showUser).execute(captor.capture(), eq(ShowUser.Params.fromSource(false)))
        captor.firstValue.onNext(user)
        assertEquals(userView, viewModel.getUser().value?.data)
    }

}
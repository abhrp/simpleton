package com.github.abhrp.remote

import com.github.abhrp.data.model.UserEntity
import com.github.abhrp.remote.factory.UserFactory
import com.github.abhrp.remote.mapper.UserResponseMapper
import com.github.abhrp.remote.model.User
import com.github.abhrp.remote.service.UserApiService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserRemoteImlTest {

    private val mapper = mock<UserResponseMapper>()
    private val service = mock<UserApiService>()

    private val remoteImpl = UserRemoteImpl(service, mapper)

    @Test
    fun getUserCompletes() {
        stubUserResponse(Observable.just(UserFactory.makeUser()))
        stubMapper(any(), UserFactory.makeUserEntity())
        val testObserver = remoteImpl.getUser().test()
        testObserver.assertComplete()
    }

    @Test
    fun getUsersApiCalled() {
        stubUserResponse(Observable.just(UserFactory.makeUser()))
        stubMapper(any(), UserFactory.makeUserEntity())
        remoteImpl.getUser().test()
        verify(service).getUser()
    }

    @Test
    fun getUsersReturnsData() {
        val response = UserFactory.makeUser()
        stubUserResponse(Observable.just(response))
        val entity = UserFactory.makeUserEntity()
        stubMapper(response, entity)
        val testObserver = remoteImpl.getUser().test()
        testObserver.assertValue(entity)
    }

    private fun stubUserResponse(observable: Observable<User>) {
        whenever(service.getUser()).thenReturn(observable)
    }

    private fun stubMapper(model: User, UserEntity: UserEntity) {
        whenever(mapper.mapToEntity(model)).thenReturn(UserEntity)
    }

}
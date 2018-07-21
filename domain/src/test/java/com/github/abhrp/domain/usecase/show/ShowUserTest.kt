package com.github.abhrp.domain.usecase.show

import com.github.abhrp.domain.PostExecutionThread
import com.github.abhrp.domain.factory.UserFactory
import com.github.abhrp.domain.model.User
import com.github.abhrp.domain.repository.UserRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ShowUserTest {
    private lateinit var showUser: ShowUser

    @Mock lateinit var userRepository: UserRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        showUser = ShowUser(userRepository, postExecutionThread)
    }

    @Test
    fun testShowUserCompletes() {
        stubShowUser(Observable.just(UserFactory.makeUser()))
        val testObserver = showUser.buildUseCaseObservable(ShowUser.Params.fromSource(false)).test()
        testObserver.assertComplete()
    }

    @Test
    fun testShowUserReturnsData() {
        val data = UserFactory.makeUser()
        stubShowUser(Observable.just(data))
        val testObserver = showUser.buildUseCaseObservable(ShowUser.Params.fromSource(false)).test()
        testObserver.assertValue(data)
    }

    private fun stubShowUser(observable: Observable<User>) {
        whenever(userRepository.getUserInfo(false)).thenReturn(observable)
    }

}
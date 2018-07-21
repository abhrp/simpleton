package com.github.abhrp.domain.usecase.delete

import com.github.abhrp.domain.PostExecutionThread
import com.github.abhrp.domain.repository.UserRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DeleteUserTest {
    private lateinit var deleteUser: DeleteUser

    @Mock
    lateinit var userRepository: UserRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deleteUser = DeleteUser(userRepository, postExecutionThread)
    }

    @Test
    fun testDeleteUserCompletes() {
        stubDeleteUser(Completable.complete(), "abc")
        val testObserver = deleteUser.buildUseCaseCompletable(DeleteUser.Params.forUser("abc")).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun deleteThrowsException() {
        deleteUser.buildUseCaseCompletable().test()
    }

    @Test(expected = IllegalArgumentException::class)
    fun deleteThrowsExceptionIfEmptyArgument() {
        stubDeleteUser(Completable.complete(), "")
        deleteUser.buildUseCaseCompletable(DeleteUser.Params.forUser("")).test()
    }


    private fun stubDeleteUser(completable: Completable, id: String) {
        whenever(userRepository.deleteUser(id)).thenReturn(completable)
    }

}
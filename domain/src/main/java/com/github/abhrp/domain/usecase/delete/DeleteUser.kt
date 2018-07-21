package com.github.abhrp.domain.usecase.delete

import com.github.abhrp.domain.PostExecutionThread
import com.github.abhrp.domain.repository.UserRepository
import com.github.abhrp.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class DeleteUser @Inject constructor(private val userRepository: UserRepository, postExecutionThread: PostExecutionThread): CompletableUseCase<DeleteUser.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        params?.let {
            if (it.id.isEmpty()) {
                throw IllegalArgumentException("User id cannot be empty or null")
            }
            return userRepository.deleteUser(it.id)
        }
        throw IllegalArgumentException("Params cannot be null")
    }

    data class Params constructor(val id: String) {
        companion object {
            fun forUser(id: String): Params = Params(id)
        }
    }

}
package com.github.abhrp.domain.usecase.show

import com.github.abhrp.domain.PostExecutionThread
import com.github.abhrp.domain.model.User
import com.github.abhrp.domain.repository.UserRepository
import com.github.abhrp.domain.usecase.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ShowUser @Inject constructor(private val userRepository: UserRepository, postExecutionThread: PostExecutionThread) : ObservableUseCase<User, ShowUser.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<User> {
        val forceRemote = if (params == null) false else params.forceRemote
        return userRepository.getUserInfo(forceRemote)
    }

    data class Params constructor(val forceRemote: Boolean = false) {
        companion object {
            fun fromSource(forceRemote: Boolean): Params = Params(forceRemote)
        }
    }
}
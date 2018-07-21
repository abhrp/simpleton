package com.github.abhrp.domain.usecase.show

import com.github.abhrp.domain.PostExecutionThread
import com.github.abhrp.domain.model.User
import com.github.abhrp.domain.repository.UserRepository
import com.github.abhrp.domain.usecase.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ShowUser @Inject constructor(private val userRepository: UserRepository, postExecutionThread: PostExecutionThread): ObservableUseCase<User, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<User> {
        return userRepository.getUserInfo()
    }
}
package com.github.abhrp.data

import com.github.abhrp.data.factory.UserFactory
import com.github.abhrp.data.mapper.UserMapper
import com.github.abhrp.data.model.UserEntity
import com.github.abhrp.data.repository.UserCache
import com.github.abhrp.data.store.UserCacheDataStore
import com.github.abhrp.data.store.UserDataStoreFactory
import com.github.abhrp.domain.model.User
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserDataRepositoryTest {
    private val mapper = mock<UserMapper>()
    private val factory = mock<UserDataStoreFactory>()
    private val cache = mock<UserCache>()
    private val store = mock<UserCacheDataStore>()
    private val repository = UserDataRepository(cache, factory, mapper)

    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsUserCached(Single.just(false))
        stubCacheIsExpired(Single.just(false))
        saveUserToCache(Completable.complete())
    }

    @Test
    fun getUsersComplete() {
        stubGetUser(Observable.just(UserFactory.makeUserEntity()))
        stubMapper(UserFactory.makeUser(), any())
        val testObserver = repository.getUserInfo(false).test()
        testObserver.assertComplete()
    }

    @Test
    fun getUsersReturnsData() {
        val userEntity = UserFactory.makeUserEntity()
        val user = UserFactory.makeUser()

        stubGetUser(Observable.just(userEntity))
        stubMapper(user, userEntity)

        val testObserver = repository.getUserInfo(false).test()
        testObserver.assertValue(user)
    }

    @Test
    fun deleteUser() {
        stubDeleteUser(Completable.complete())
        val observer = cache.deleteUser("abc").test()
        observer.assertComplete()
    }


    private fun stubMapper(model: User, entity: UserEntity) {
        whenever(mapper.mapToData(entity))
                .thenReturn(model)
    }

    private fun stubGetUser(observable: Observable<UserEntity>) {
        whenever(store.getUser())
                .thenReturn(observable)
    }


    private fun stubFactoryGetDataStore() {
        whenever(factory.getUserDataStore(any(), any(), any()))
                .thenReturn(store)
    }

    private fun stubCacheIsExpired(expired: Single<Boolean>) {
        whenever(cache.isCacheExpired())
                .thenReturn(expired)
    }

    private fun stubIsUserCached(cached: Single<Boolean>) {
        whenever(cache.isUserCached())
                .thenReturn(cached)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getUserCacheDataStore())
                .thenReturn(store)
    }

    private fun saveUserToCache(completable: Completable) {
        whenever(store.saveUser(any()))
                .thenReturn(completable)
    }

    private fun stubDeleteUser(completable: Completable) {
        whenever(cache.deleteUser(any()))
                .thenReturn(completable)
    }
}
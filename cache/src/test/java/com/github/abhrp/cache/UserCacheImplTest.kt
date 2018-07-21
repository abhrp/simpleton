package com.github.abhrp.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.github.abhrp.cache.factory.UserDataFactory
import com.github.abhrp.cache.mapper.UserEntityMapper
import com.github.abhrp.cache.preferences.SimpletonSharedPreferences
import com.nhaarman.mockito_kotlin.mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class UserCacheImplTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room
            .inMemoryDatabaseBuilder(RuntimeEnvironment.application.applicationContext, SimpletonDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    private val cachedUserMapper = UserEntityMapper()
    private val sharedPreferences = mock<SimpletonSharedPreferences>()

    private val cache = UserCacheImpl(database, cachedUserMapper, sharedPreferences)

    @Test
    fun deleteUsersCompletes() {
        val testObserver = cache.deleteUser("abc").test()
        testObserver.assertComplete()
    }

    @Test
    fun saveUsersCompletes() {
        val user = UserDataFactory.makeUserEntity()
        val testObserver = cache.saveUser(user).test()
        testObserver.assertComplete()
    }

    @Test
    fun getUsersReturnsData() {
        val user = UserDataFactory.makeUserEntity()
        cache.saveUser(user).test()
        val testObserver = cache.getUser().test()
        testObserver.assertValue(user)
    }

    @Test
    fun setRemoveUserCompletes() {
        val user = UserDataFactory.makeUserEntity()
        cache.saveUser(user).test()
        val testObserver = cache.deleteUser(user.id).test()
        testObserver.assertComplete()
    }

    @Test
    fun areUsersCachedReturnsData() {
        val user = UserDataFactory.makeUserEntity()
        cache.saveUser(user).test()
        val testObserver = cache.isUserCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTime() {
        val testObserver = cache.setLastCacheTime(System.currentTimeMillis()).test()
        testObserver.assertComplete()
    }

    @Test
    fun isUsersCacheExpiredReturnsExpired() {
        val testObserver = cache.isCacheExpired().test()
        testObserver.assertValue(true)
    }
}
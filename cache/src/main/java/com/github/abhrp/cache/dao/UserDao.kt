package com.github.abhrp.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.abhrp.cache.constants.DatabaseConstants
import com.github.abhrp.cache.entity.UserInfoEntity
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Query(DatabaseConstants.QUERY_SELECT_ALL)
    fun getAllUsers(): Flowable<List<UserInfoEntity>>

    @Query(DatabaseConstants.QUERY_SELECT_USER)
    fun getUser(id: String): Flowable<UserInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(userInfoEntity: UserInfoEntity)

    @Query(DatabaseConstants.QUERY_DELETE_ALL)
    fun deleteAllUsers()

    @Query(DatabaseConstants.QUERY_DELETE_USER)
    fun deleteUser(id: String)
}
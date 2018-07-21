package com.github.abhrp.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.abhrp.cache.constants.DatabaseConstants
import com.github.abhrp.cache.entity.ConfigEntity
import io.reactivex.Flowable

@Dao
interface ConfigDao {

    @Query(DatabaseConstants.QUERY_SELECT_CONFIG)
    fun getConfig(): Flowable<ConfigEntity>

    @Query(DatabaseConstants.QUERY_DELETE_CONFIG)
    fun deleteConfig()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveConfig(configEntity: ConfigEntity)
}
package com.github.abhrp.cache.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.github.abhrp.cache.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.CONFIG_TABLE)
data class ConfigEntity(@PrimaryKey @ColumnInfo(name = DatabaseConstants.LAST_CACHE_TIME_COL) val lastCacheTime: Long)
package com.github.abhrp.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.github.abhrp.cache.dao.ConfigDao
import com.github.abhrp.cache.dao.UserDao
import com.github.abhrp.cache.entity.ConfigEntity
import com.github.abhrp.cache.entity.UserInfoEntity

@Database(entities = [(UserInfoEntity::class), (ConfigEntity::class)], version = 1)
abstract class SimpletonDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getConfigDao(): ConfigDao

    companion object {
        private var INSTANCE: SimpletonDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): SimpletonDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, SimpletonDatabase::class.java, "simpleton.db").build()
                    }
                    return INSTANCE as SimpletonDatabase
                }
            }
            return INSTANCE as SimpletonDatabase
        }
    }

}
package com.github.abhrp.cache.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.github.abhrp.cache.constants.DatabaseConstants


@Entity(tableName = DatabaseConstants.USER_TABLE)
data class UserInfoEntity(@PrimaryKey val id: String,
                          @ColumnInfo(name = DatabaseConstants.FIRST_NAME_COL) val firstName: String,
                          @ColumnInfo(name = DatabaseConstants.LAST_NAME_COL) val lastName: String,
                          @ColumnInfo(name = DatabaseConstants.PHONE_NUMBER_COL) val phoneNumber: String,
                          val email: String,
                          @ColumnInfo(name = DatabaseConstants.PROFILE_PICTURE_COL) val profilePicture: String)
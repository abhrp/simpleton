package com.github.abhrp.cache.constants

object DatabaseConstants {
    const val USER_TABLE = "user"
    const val FIRST_NAME_COL = "first_name"
    const val LAST_NAME_COL = "last_name"
    const val PHONE_NUMBER_COL = "phone_number"
    const val PROFILE_PICTURE_COL = "profile_picture"

    const val CONFIG_TABLE = "config"
    const val LAST_CACHE_TIME_COL = "last_cache_time"

    const val QUERY_SELECT_ALL = "select * from $USER_TABLE"
    const val QUERY_SELECT_USER = "select * from $USER_TABLE where id=:id"
    const val QUERY_DELETE_ALL = "delete from $USER_TABLE"
    const val QUERY_DELETE_USER = "delete from $USER_TABLE where id=:id"

    const val QUERY_SELECT_CONFIG = "select * from $CONFIG_TABLE"
    const val QUERY_DELETE_CONFIG = "delete from $CONFIG_TABLE"

}
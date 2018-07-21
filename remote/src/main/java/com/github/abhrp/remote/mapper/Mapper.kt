package com.github.abhrp.remote.mapper

interface Mapper<in M, out E> {
    fun mapToEntity(model: M): E
}
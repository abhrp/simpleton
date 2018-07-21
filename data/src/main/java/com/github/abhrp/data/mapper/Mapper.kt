package com.github.abhrp.data.mapper

interface Mapper<E, D> {
    fun mapToEntity(data: D): E
    fun mapToData(entity: E): D
}
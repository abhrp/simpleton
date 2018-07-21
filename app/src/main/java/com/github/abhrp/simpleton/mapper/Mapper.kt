package com.github.abhrp.simpleton.mapper

interface Mapper<in V, out M> {
    fun mapToModel(viewModel: V): M
}
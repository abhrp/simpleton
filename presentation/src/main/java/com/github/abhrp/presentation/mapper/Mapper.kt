package com.github.abhrp.presentation.mapper

interface Mapper<in D, out V> {
    fun mapToModelView(domain: D): V
}
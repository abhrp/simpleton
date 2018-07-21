package com.github.abhrp.presentation.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {
    fun randomString(): String = UUID.randomUUID().toString()
    fun randomLong(): Long = ThreadLocalRandom.current().nextInt(0, 1000 + 1).toLong()
}
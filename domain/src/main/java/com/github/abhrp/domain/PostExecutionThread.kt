package com.github.abhrp.domain

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}
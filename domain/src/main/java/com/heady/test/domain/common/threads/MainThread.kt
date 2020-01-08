package com.heady.test.domain.common.threads

import io.reactivex.Scheduler

/**
 * To Be Implemented On Presentation Layer, Responsible For Providing Main/UI Thread
 *
 * Created by Praveen.
 */
interface MainThread {
    val scheduler: Scheduler
}
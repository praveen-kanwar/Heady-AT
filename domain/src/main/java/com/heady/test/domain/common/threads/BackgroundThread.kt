package com.heady.test.domain.common.threads

import io.reactivex.Scheduler

/**
 * To Be Implemented On Data Layer, Responsible For Providing Task Execution Thread
 *
 * Created by Praveen.
 */
interface BackgroundThread {
    val scheduler: Scheduler
}
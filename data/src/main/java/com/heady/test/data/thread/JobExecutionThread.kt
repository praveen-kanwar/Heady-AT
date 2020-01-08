package com.heady.test.data.thread

import com.heady.test.domain.common.threads.BackgroundThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Responsible For Providing Threads From Thread Pool For Task Execution On Data Layer
 *
 * Created by Praveen on 01-07-2019.
 */
class JobExecutionThread
@Inject
constructor() : BackgroundThread {
    override val scheduler: Scheduler get() = Schedulers.io()
}
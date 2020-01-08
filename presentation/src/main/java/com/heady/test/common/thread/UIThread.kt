package com.heady.test.common.thread

import com.heady.test.domain.common.threads.MainThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Responsible For Providing UI Thread
 *
 * Created by Praveen.
 */
@Singleton
class UIThread
@Inject
constructor() : MainThread {
    override val scheduler: Scheduler get() = AndroidSchedulers.mainThread()
}
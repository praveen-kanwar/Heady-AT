package com.heady.test.common.firebase.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Firebase Notification Worker Class For Performing Long Running Background Task On Reliable Thread.
 *
 * Created by Praveen.
 */
class FirebaseNotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Log.e(TAG, "Doing Long Running Background Work")
        return Result.success()
    }

    companion object {
        private const val TAG = "NotificationWorker"
    }
}
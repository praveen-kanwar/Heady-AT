package com.heady.test.common.firebase.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.heady.test.BuildConfig
import com.heady.test.R
import com.heady.test.base.activity.BaseActivity
import com.heady.test.common.firebase.workmanager.FirebaseNotificationWorker

/**
 * Firebase Notification Service Implementation
 *
 * Created by Praveen.
 */
class FirebaseNotificationService : FirebaseMessagingService() {

    /**
     * Called When Notification Is Received
     *
     * @param message String to be printed In Log
     */
    private fun showLog(message: String) {
        @Suppress("ConstantConditionIf")
        if (BuildConfig.DEBUG) {
            Log.e(TAG, message)
        }
    }

    /**
     * Called When Notification Is Received
     *
     * @param remoteMessage Object Representing The Message Received From Firebase Cloud Messaging.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Check Message Received From
        showLog("Message From: ${remoteMessage.from}")
        // Check If Message Contains Data
        remoteMessage.data.isNotEmpty().let {
            showLog("Message data payload: ${remoteMessage.data}")
            // Check If Data Need To Be Processed Right Not Or Via Service(i.e Long Running Task)
            /*if (true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob()
            } else {
                // Handle message within 10 seconds
                handleNow()
            }*/
            scheduleJob()
            handleNow()
        }
        // Check If Message Contains Payload
        remoteMessage.notification?.let { remoteMessageNotification ->
            showLog("Message Notification Body: ${remoteMessageNotification.body}")
            sendNotification(remoteMessage)
        }

    }

    /**
     * Called When New Messaging Token Is Generated Due To Security Breach.
     *
     * @param token New Token For Messaging
     */
    override fun onNewToken(token: String) {
        showLog("onNewToken($token)")
        sendRegistrationToServer(token)
    }

    /**
     * To Schedule A Long Running Background Task
     */
    private fun scheduleJob() {
        val work = OneTimeWorkRequest.Builder(FirebaseNotificationWorker::class.java).build()
        WorkManager.getInstance(this).beginWith(work).enqueue()
    }

    /**
     * Handle Task Right Now
     */
    private fun handleNow() {
        showLog("Short lived task is done.")
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String?) {
        // Implement this method to send token to your app server.
        showLog("Registration Token $token")
    }

    /**
     * Create & Show Simple Notification Of Received Message.
     *
     * @param remoteMessage Remote Message Received.
     */
    private fun sendNotification(remoteMessage: RemoteMessage?) {
        val intent = Intent(this, BaseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val channelId = getString(R.string.notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(remoteMessage!!.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Since android Oreo Notification Channel Is Needed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                remoteMessage.notification!!.title,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }

    companion object {
        private const val TAG = "NotificationService"
    }
}
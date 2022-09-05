package com.boo.sample.samplebackground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat

class JobService : JobIntentService() {
    val CHANNEL_ID = "Job Channel"

    val notificationManager by lazy { getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }

    override fun onHandleWork(intent: Intent) {
        createNotificationChannel()
        try {
            var num = 0
            for(count in 1..100) {
                num++
                //UI 갱신을 위해서 콜백
                showNotification(num)
                Thread.sleep(100)
            }
        } catch (e: Exception) {

        }
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "job channel"
            val descriptionText = "job channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(progress : Int) {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Job Service")
            .setProgress(100, progress, false)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(2, notification)
    }

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            JobIntentService.enqueueWork(context, JobService::class.java, 1000, intent)
        }
    }
}
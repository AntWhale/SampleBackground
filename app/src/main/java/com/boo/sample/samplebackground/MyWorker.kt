package com.boo.sample.samplebackground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    val CHANNEL_ID = "Job Channel"
    val notificationManager by lazy { context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }

    init {
        createNotificationChannel()
    }

    override fun doWork(): Result {
        try {//background
            var num = 0
            for(count in 1 ..100) {
                num++
                val data = Data.Builder()
                    .putInt("progress", num)
                    .build()
                setProgressAsync(data)
                //UI 갱신을 위해서 콜백
                showNotification(num)
                Thread.sleep(100)
            }
        } catch (e: Exception) {
            Result.failure()
        }
        return Result.success()
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
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("WorkManager long task")
            .setProgress(100, progress, false)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(3, notification)
    }
}
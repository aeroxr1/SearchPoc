package com.aeroxr1.notificationmodule

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

object NotificationUtility {

    const val CHANNEL_ID = "4545"
    const val NOTIFICATION_ID = 789

    fun showNotification(application: Application){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "testChannel"
            val descriptionText = "testDescription"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            val notificationManager =
                application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID,
                name,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setSound(null, null)
            notificationManager.createNotificationChannel(channel)
        }

        var builder = NotificationCompat.Builder(application, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("textTitle")
            .setContentText("textContent")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(application)) {
            // notificationId is a unique int for each notification that you must define
            notify(NOTIFICATION_ID, builder.build())
        }
    }

}
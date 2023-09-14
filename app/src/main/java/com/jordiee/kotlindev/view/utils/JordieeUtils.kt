package com.jordiee.kotlindev.view.utils

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jordiee.kotlindev.R
import com.jordiee.kotlindev.view.MainActivity
import com.jordiee.kotlindev.view.broadcastReceiver.JordieeBroadcastReceiver

class JordieeUtils {
    companion object {

        private const val NOTIFICATION_CHANNEL_ID = "1"
        private const val NOTIFICATION_CHANNEL_NAME = "JordieeLocalNotifications"
         fun displayNotification(context : Context) {
            val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
             val intent = Intent(context.applicationContext, MainActivity::class.java)
             val actionIntent = Intent(context.applicationContext, JordieeBroadcastReceiver::class.java)
             actionIntent.action = "NOTIFICATION_ACTION"

             val actionPendingIntent = PendingIntent.getBroadcast(context, 1003, actionIntent, PendingIntent.FLAG_IMMUTABLE)
             val pendingIntent = PendingIntent.getActivity(context, 1002, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                builder.setSmallIcon(R.drawable.bell)
                    .setContentTitle("Jordiee's Notification")
                    .setContentText("Here is the notification")
                    .setContentIntent(pendingIntent)
                    .addAction(R.drawable.bell,"Dismiss", actionPendingIntent)
                    .setAutoCancel(true)
                val manager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val channel = NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                manager.createNotificationChannel(channel)
            } else {
                builder.setSmallIcon(R.drawable.bell)
                    .setContentTitle("Jordiee's Notification")
                    .setContentText("Here is the notification")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .priority = NotificationManager.IMPORTANCE_HIGH
            }
            val notificationManagerCompat = NotificationManagerCompat.from(context)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                (context as Activity).requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
            } else {
                notificationManagerCompat.notify(1, builder.build())
                builder.build()
            }
        }
    }
}
package com.jordiee.kotlindev.view.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.jordiee.kotlindev.view.utils.JordieeUtils

class JordieeBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals("JordieeReminder")) {
            if (context != null)
                JordieeUtils.displayNotification(context)
        } else if (intent?.action.equals("NOTIFICATION_ACTION")) {
            context?.let {
                val notificationMananger = NotificationManagerCompat.from(it)
                notificationMananger.cancel(1)
            }

        }
    }
}
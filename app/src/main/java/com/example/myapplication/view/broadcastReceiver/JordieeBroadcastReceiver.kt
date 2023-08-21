package com.example.myapplication.view.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.myapplication.view.utils.JordieeUtils

class JordieeBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals("JordieeReminder")) {
            if (context != null)
                JordieeUtils.displayNotification(context)
        }
    }
}
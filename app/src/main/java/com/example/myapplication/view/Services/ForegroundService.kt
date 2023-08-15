package com.example.myapplication.view.Services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.R

class ForegroundService : Service() {
    var isRunning = true;
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("jordiee", "Foreground service started")
        Thread {
            while (isRunning) {
                Log.i("jordiee", "foreground service running")
                Thread.sleep(2000)
            }
        }.start()
        val channelId = "ForegroundServiceId"
        val channel = NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH)
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, channelId)
            .setContentText("Foreground service running")
            .setContentTitle("Service running")
            .setSmallIcon(R.drawable.london)
            .build()
        startForeground(1001, notification)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        Log.i("jordiee", "Foreground service created")
        super.onCreate()
    }

    override fun onDestroy() {
        Log.i("jordiee", "Foreground service destroyed")
        isRunning = false
        super.onDestroy()
    }
}
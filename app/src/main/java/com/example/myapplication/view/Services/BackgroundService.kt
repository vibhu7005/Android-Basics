package com.example.myapplication.view.Services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService : Service() {
    var isRunning = true;
    override fun onBind(intent: Intent?): IBinder? {
        Log.i("jordiee", "background service binded")
        return null
    }

    override fun onCreate() {
        Log.i("jordiee", "background service created")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("jordiee", "background service started")
        Thread {
            while (isRunning) {
                Log.i("jordiee"," Service is running")
                Thread.sleep(2000)
            }
        }.start()
        return super.onStartCommand(intent, flags, startId)
    };

    override fun onDestroy() {
        Log.i("jordiee", "background service destroyed")
        isRunning = false
        super.onDestroy()
    }
}
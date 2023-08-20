package com.example.myapplication.view

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityThirdBinding
import com.example.myapplication.view.Services.ForegroundService


private const val NOTIFICATION_CHANNEL_ID = "1"
private const val NOTIFICATION_CHANNEL_NAME = "JordieeLocalNotifications"

class ThirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, ForegroundService::class.java)
        binding.btnServiceStart.setOnClickListener {
            startService(intent)
        }
        binding.btnStopService.setOnClickListener {
            stopService(intent)
//            intent.action = "stop"
//            startService(intent)
//            stop(Intent(this, ForegroundService::class.java))
        }
        binding.btnNotifications.setOnClickListener {
            displayNotification()
        }
    }

    private fun displayNotification() {
        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            builder.setSmallIcon(R.drawable.bell)
                .setContentTitle("Jordiee's Notification")
                .setContentText("Here is the notification")
            val manager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        } else {
            builder.setSmallIcon(R.drawable.bell)
                .setContentTitle("Jordiee's Notification")
                .setContentText("Here is the notification").priority =
                NotificationManager.IMPORTANCE_HIGH
        }
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        } else {
            notificationManagerCompat.notify(1, builder.build())
            builder.build()
        }
    }
}
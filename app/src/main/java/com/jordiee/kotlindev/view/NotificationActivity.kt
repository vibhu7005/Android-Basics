package com.jordiee.kotlindev.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jordiee.kotlindev.databinding.ActivityThirdBinding
import com.jordiee.kotlindev.view.notesapp.NotesActivity
import com.jordiee.kotlindev.view.Services.ForegroundService
import com.jordiee.kotlindev.view.broadcastReceiver.JordieeBroadcastReceiver
import com.jordiee.kotlindev.view.utils.JordieeUtils
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar


class NotificationActivity : AppCompatActivity() {
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
        setPeriodicNotification()
        binding.btnNotifications.setOnClickListener {
            JordieeUtils.displayNotification(this)
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

        binding.sendPeriodicNotification.setOnClickListener {
            val calender = Calendar.getInstance()
            val timePicker = MaterialTimePicker.Builder().
            setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(calender.get(Calendar.HOUR_OF_DAY))
                .setMinute(calender.get(Calendar.MINUTE))
                .build()
            timePicker.show(supportFragmentManager, "Time")
            timePicker.addOnPositiveButtonClickListener {
                calender.set(Calendar.HOUR_OF_DAY, timePicker.hour)
                calender.set(Calendar.MINUTE, timePicker.minute)
                calender.set(Calendar.SECOND, 0)
                val timerIntent = Intent(this, JordieeBroadcastReceiver::class.java)
                timerIntent.action = "JordieeReminder"
                val pendingIntent = PendingIntent.getBroadcast(this, 1001, timerIntent, PendingIntent.FLAG_IMMUTABLE)
                val alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmManager.set(AlarmManager.RTC_WAKEUP, calender.timeInMillis, pendingIntent)
            }

        }
    }

    private fun setPeriodicNotification() {


    }
}
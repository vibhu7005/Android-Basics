package com.example.myapplication.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityThirdBinding
import com.example.myapplication.view.Services.BackgroundService
import com.example.myapplication.view.Services.ForegroundService


class ThirdActivity : AppCompatActivity() {
    lateinit var binding : ActivityThirdBinding
    @RequiresApi(Build.VERSION_CODES.O)
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
    }
}
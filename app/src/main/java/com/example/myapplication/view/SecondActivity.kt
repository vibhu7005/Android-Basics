package com.example.myapplication.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var  activityBinding : ActivitySecondBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        activityBinding.btCounter.setOnClickListener {
            activityBinding.btCounter.text = (Integer.parseInt(activityBinding.btCounter.text.toString()) + 1).toString()
        }

        activityBinding.btnNext.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}
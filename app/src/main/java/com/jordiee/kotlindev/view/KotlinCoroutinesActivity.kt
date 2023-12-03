package com.jordiee.kotlindev.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jordiee.kotlindev.R
import com.jordiee.kotlindev.databinding.ActivityKotlinCoroutinesBinding

class KotlinCoroutinesActivity : AppCompatActivity() {
    lateinit var binding : ActivityKotlinCoroutinesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
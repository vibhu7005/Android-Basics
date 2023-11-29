package com.jordiee.kotlindev.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.await
import com.jordiee.kotlindev.R
import com.jordiee.kotlindev.databinding.ActivityWorkManagerBinding
import com.jordiee.kotlindev.model.JordieeViewModel
import com.jordiee.kotlindev.view.workmanager.BlurWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class WorkManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkManagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val request = OneTimeWorkRequest.Builder(BlurWorker::class.java).build()

        binding.btnImageBlur.setOnClickListener {
            val manager = WorkManager.getInstance(this)
            manager.enqueue(request)
            manager.getWorkInfoByIdLiveData(request.id)
                .observe(this, Observer {
                    it.outputData
                })
            val viewModel = ViewModelProvider(this)[JordieeViewModel::class.java]
            viewModel.workResultLiveData.observe(this) {
                Log.d("vaibhav", ""+it)
            }
            binding.image.setImageBitmap(BlurWorker.outputBimtmap)
        }
    }

}
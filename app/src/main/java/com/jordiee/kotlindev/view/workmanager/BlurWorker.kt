package com.jordiee.kotlindev.view.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class BlurWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    companion object {
          var outputBimtmap : Bitmap? =null
    }
    @SuppressLint("WrongThread")
    override fun doWork(): Result {
        return try {
            Thread.sleep(5000)
            val outputData = Data.Builder()
                .putString("value", "T4")
                .build()
            Result.success(outputData)
        } catch (ex : Exception) {
            Result.failure()
        }
    }

}
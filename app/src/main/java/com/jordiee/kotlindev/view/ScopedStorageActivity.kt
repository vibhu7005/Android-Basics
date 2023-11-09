package com.jordiee.kotlindev.view

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import com.jordiee.kotlindev.R
import com.jordiee.kotlindev.databinding.ActivityScoupedStorageBinding
import java.io.OutputStream

class ScopedStorageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoupedStorageBinding
    private lateinit var resLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoupedStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCamera.setOnClickListener {
            openCamera()
        }
        registerActivityForResult()
    }

    private fun registerActivityForResult() {
        resLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                storeBitmap(it.data as Bitmap)
            }
        }
    }

    private fun storeBitmap(bitmap: Bitmap) {
        val imageCollection = if (Build.VERSION.SDK_INT >= VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        }
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "Jordiee")
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "jpeg")
        val imageUri =contentResolver.insert(imageCollection, contentValues)
        imageUri?.let {
            val outputStream = contentResolver.openOutputStream(it)
            if (outputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
            }
            outputStream?.close()
            contentValues.clear()
            contentResolver.update(it, contentValues, null, null)
        }

    }

    private fun openCamera() {
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        resLauncher.launch(intent)
    }


}
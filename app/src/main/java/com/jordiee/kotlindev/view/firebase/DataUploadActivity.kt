package com.jordiee.kotlindev.view.firebase

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.storage.FirebaseStorage

import com.jordiee.kotlindev.databinding.ActivityDataUploadBinding
import com.squareup.picasso.Picasso
import java.util.UUID

class DataUploadActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDataUploadBinding
    private var imageUri : Uri? = null
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val firebaseStorage = FirebaseStorage.getInstance()
    private val storageReference = firebaseStorage.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvChooseImage.setOnClickListener {
           chooseImage()
        }
        binding.btnUploadImage.setOnClickListener {
            uploadImage()
        }
        registerActivityForResult()
    }

    private fun chooseImage() {
        if (ContextCompat.checkSelfPermission(this@DataUploadActivity, android.Manifest.permission.READ_MEDIA_IMAGES)!= PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(this@DataUploadActivity, arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES),1)
            } else {
                ActivityCompat.requestPermissions(this@DataUploadActivity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            }
        } else {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(intent)

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == (PackageManager.PERMISSION_GRANTED)) {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(intent)
        }
    }

    private fun uploadImage() {
        imageUri?.let {
            val filename = UUID.randomUUID().toString()
            storageReference.child("images").child(filename)
                .putFile(it).addOnSuccessListener {
                    Toast.makeText(this, "Image uploaded", Toast.LENGTH_SHORT).show()
                    val downloadUrl = storageReference.child("images").child(filename)
                        .downloadUrl.addOnSuccessListener { url ->
                            addUserToDatabase(url)
                        }

                }
                .addOnFailureListener {it->
                    Toast.makeText(this, "Image upload failed", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun addUserToDatabase(image : Uri) {

    }


    private fun registerActivityForResult() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {callback ->
            val resultCode = callback.resultCode
            val imageData = callback.data
            if (resultCode == RESULT_OK && imageData != null) {
               imageUri = imageData.data
                Picasso.get().load(imageUri).into(binding.ivUserImage)
            }
        }
    }
}
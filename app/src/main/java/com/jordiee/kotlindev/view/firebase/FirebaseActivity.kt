package com.jordiee.kotlindev.view.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase

import com.jordiee.kotlindev.databinding.ActivityFirebaseBinding

class FirebaseActivity : AppCompatActivity() {
    val database  = FirebaseDatabase.getInstance()
    val references = database.reference.child("Users")

    private lateinit var binding: ActivityFirebaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btAdd.setOnClickListener {
            references.child("Age").setValue(binding.etName.text.toString())
        }
    }
}
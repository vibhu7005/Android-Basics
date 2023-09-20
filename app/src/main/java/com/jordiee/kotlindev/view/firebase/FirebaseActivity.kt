package com.jordiee.kotlindev.view.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

import com.jordiee.kotlindev.databinding.ActivityFirebaseBinding

class FirebaseActivity : AppCompatActivity() {
    private val database  = FirebaseDatabase.getInstance()
    private val usersReference = database.reference.child("Users")
    private val databaseReference = database.reference

    private lateinit var binding: ActivityFirebaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btAdd.setOnClickListener {
            usersReference.child("Name").setValue(binding.etName.text.toString())
        }

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.etName.setText(snapshot.child("Users").child("Name").value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
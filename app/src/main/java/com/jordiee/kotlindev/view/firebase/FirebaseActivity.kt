package com.jordiee.kotlindev.view.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

import com.jordiee.kotlindev.databinding.ActivityFirebaseBinding
import com.jordiee.kotlindev.view.ScopedStorageActivity
import com.jordiee.kotlindev.view.WorkManagerActivity
import com.jordiee.kotlindev.view.firebase.data.Users

class FirebaseActivity : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance()
    private val databaseReference = database.reference.child("Users")

    private lateinit var binding: ActivityFirebaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btAdd.setOnClickListener { addUserToDatabase() }
        binding.btUploadFirebase.setOnClickListener {
            val intent = Intent(this, DataUploadActivity::class.java)
            startActivity(intent)
        }
        binding.btNext.setOnClickListener {
            val intent = Intent(this, WorkManagerActivity::class.java)
            startActivity(intent)
        }
        updateData()
        fetchUsersData()
        removeData()
    }

    private fun updateData() {
        val map: MutableMap<String?, Any?> = mutableMapOf()
        map["email"] = "updatedTwice"
        databaseReference.child("-NhumGZtUVSg4liF1Suk").updateChildren(map)
    }

    private fun removeData() {
        databaseReference.child("-NhumGZtUVSg4liF1Suk").removeValue()
    }
    private fun addUserToDatabase() {
        val id = databaseReference.push().key.toString()
        val user = Users(id, binding.etName.text.toString(), binding.etEmail.text.toString())
       databaseReference.child(id).setValue(user).addOnCompleteListener {
           if (it.isSuccessful) {
               Toast.makeText(this, "added", Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
           }
       }
    }

   private fun fetchUsersData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = snapshot.children
                for (data in users) {
                    val user = data.getValue(Users::class.java)
                    println(user?.id)
                    println(user?.name)
                    println(user?.email)
                    println()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun retrieveData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (user in snapshot.children) {
                    val userInfo = user.getValue(Users::class.java)
                    println(userInfo?.name)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
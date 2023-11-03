package com.jordiee.kotlindev.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jordiee.kotlindev.databinding.ActivitySignUpBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
           signUpWithFirebase()
        }

        binding.btnLogin.setOnClickListener {
            signInUser()
        }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            startMainActivity()
        }
    }

    private fun signUpWithFirebase() {
        auth.createUserWithEmailAndPassword(
            binding.etEmail.text.toString(),
            binding.etPwd.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Your account has been created", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInUser() {
        auth.signInWithEmailAndPassword(
            binding.etEmail.text.toString(),
            binding.etPwd.text.toString()
        ).addOnCompleteListener {task->
            if(task.isSuccessful) {
                startMainActivity()
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
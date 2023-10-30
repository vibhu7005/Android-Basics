package com.jordiee.kotlindev.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jordiee.kotlindev.R
import com.jordiee.kotlindev.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.etEmail.text.toString(),
                binding.etPwd.text.toString()
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("logged in successfully")
                } else {
                    println("login failed")
                }
            }
        }
    }
}
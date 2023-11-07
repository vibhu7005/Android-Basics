package com.jordiee.kotlindev.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        binding.btnForgotPassword.setOnClickListener {
            resetPasswordOnFirebase()
        }

        binding.btnOtpLogin.setOnClickListener {
            openOtpLoginFlow()
        }
    }

    private fun openOtpLoginFlow() {
        val intent = Intent(this, OtpLoginActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            startMainActivity()
        }
    }

    private fun resetPasswordOnFirebase() {
        val inputEditText = EditText(this);
        AlertDialog.Builder(this)
            .setTitle("Enter Email")
            .setPositiveButton("ok") { _, _ ->
                auth.sendPasswordResetEmail(inputEditText.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this@LoginActivity, "Reset link sent", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            .setView(inputEditText)
            .create()
            .show()
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
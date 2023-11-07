package com.jordiee.kotlindev.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.jordiee.kotlindev.R
import com.jordiee.kotlindev.databinding.ActivityOtpLoginBinding
import java.util.concurrent.TimeUnit

class OtpLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationCode: String
    private lateinit var otpCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpLoginBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        otpCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.d("vaibhav", "" + p0.message)
                Toast.makeText(this@OtpLoginActivity, "Unable to send otp", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                verificationCode = p0
            }

        }
        binding.btnSubmit.setOnClickListener {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(binding.etPhone.text.toString())
                .setActivity(this)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(otpCallBack)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)

            val inputEditText = EditText(this);
            AlertDialog.Builder(this)
                .setTitle("Enter Email")
                .setPositiveButton("ok") { x, y ->
                    val userEnteredCode = inputEditText.text.toString();
                    val credential =
                        PhoneAuthProvider.getCredential(verificationCode, userEnteredCode)
                    signInWithOtp(credential)
                }
                .setView(inputEditText)
                .create()
                .show()
        }
    }


    private fun signInWithOtp(cred: PhoneAuthCredential) {
        auth.signInWithCredential(cred).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@OtpLoginActivity, "Authentication failed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
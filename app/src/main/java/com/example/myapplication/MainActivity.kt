package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDisplaySnackbar.setOnClickListener {
            displaySnackbar()
        }
        binding.btnDisplayDialog.setOnClickListener {
            displayDialog()
        }
    }

    private fun displaySnackbar() {
        Snackbar.make(binding.containerLayout, "Live freely", Snackbar.LENGTH_INDEFINITE)
            .setAction("op"){}
            .show()
    }

    private fun displayDialog() {
        AlertDialog.Builder(this).setTitle("Alert").setPositiveButton("yes") { it, _ -> it.cancel() }
            .show()
    }
}
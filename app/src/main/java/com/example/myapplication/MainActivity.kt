package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var manager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("vaibhav", "onCreateChild")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDisplaySnackbar.setOnClickListener {
            displaySnackbar()
        }
        binding.btnDisplayDialog.setOnClickListener {
            displayDialog()
        }
        binding.btnNext.setOnClickListener {
            val listFragment = ListFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.container_layout, listFragment, "listFragment").commit()
        }
    }

    override fun onPause() {
        Log.d("vaibhav", "onPauseChild")
        super.onPause()
    }

    override fun onStart() {
        Log.d("vaibhav", "onStartChild")
        super.onStart()
    }

    override fun onStop() {
        Log.d("vaibhav", "onStopChild")
        super.onStop()
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
package com.jordiee.kotlindev.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.jordiee.kotlindev.R
import com.jordiee.kotlindev.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        Log.d("vaibhav", "onCreateChild")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
//        val listFragment = CountryListFragment.newInstance()
//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, listFragment, "listFragment").addToBackStack("listFragment").commit()
    }

    override fun onResume() {
        super.onResume()
//        val intent = Intent(this, SecondActivity::class.java)
//        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu_auth, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sign_out) {
            signOutUserFromFirebase();
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOutUserFromFirebase() {
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
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
        Snackbar.make(binding.fragmentContainer, "Live freely", Snackbar.LENGTH_INDEFINITE)
            .setAction("op"){}
            .show()
    }

    private fun displayDialog() {
        AlertDialog.Builder(this).setTitle("Alert").setPositiveButton("yes") { it, _ -> it.cancel() }
            .show()
    }
}
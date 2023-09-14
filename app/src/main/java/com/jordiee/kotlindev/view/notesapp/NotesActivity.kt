package com.jordiee.kotlindev.view.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.jordiee.kotlindev.NotesApplication
import com.jordiee.kotlindev.databinding.ActivityNotesBinding
import com.jordiee.kotlindev.model.Note
import com.jordiee.kotlindev.view.firebase.FirebaseActivity
import com.jordiee.kotlindev.viewmodel.NotesViewModel
import com.jordiee.kotlindev.viewmodel.NotesViewModelFactory

class NotesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNotesBinding
    private lateinit var viewModel : NotesViewModel
    private lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelFactory = NotesViewModelFactory((application as NotesApplication).notesRepo)
        viewModel = ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]
        viewModel.myAllNotes.observe(this) {
            Log.d("vibhu", ""+it.size)
        }
        val note = Note(title = "loox", description = "kook")
        viewModel.insertNote(note)
        registerActivityResultLauncher()
    }
    fun addDataToDb(view: View) {
        val intent = Intent(this, AddDataActivity::class.java)
        addActivityResultLauncher.launch(intent)
    }

    private fun registerActivityResultLauncher() {
        addActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val resultCode = result.resultCode
            val resultData = result.data
            val note = Note(title = resultData?.getStringExtra("title").toString(),
                description = resultData?.getStringExtra("description").toString())
            viewModel.insertNote(note)
        }
    }

    fun proceed(view: View) {
        val intent = Intent(this, FirebaseActivity::class.java)
        startActivity(intent)
    }
}
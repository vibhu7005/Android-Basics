package com.example.myapplication.view.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.NotesApplication
import com.example.myapplication.databinding.ActivityNotesBinding
import com.example.myapplication.model.Note
import com.example.myapplication.viewmodel.NotesViewModel
import com.example.myapplication.viewmodel.NotesViewModelFactory

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
//        viewModel.insertNote(Note("hi", "hello"))
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
}
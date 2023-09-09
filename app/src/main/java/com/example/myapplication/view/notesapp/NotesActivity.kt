package com.example.myapplication.view.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.NotesApplication
import com.example.myapplication.databinding.ActivityNotesBinding
import com.example.myapplication.model.Note
import com.example.myapplication.viewmodel.NotesViewModel
import com.example.myapplication.viewmodel.NotesViewModelFactory

class NotesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNotesBinding
    private lateinit var viewModel : NotesViewModel

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
    }


}
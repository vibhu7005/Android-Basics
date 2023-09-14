package com.jordiee.kotlindev.view.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.jordiee.kotlindev.NotesApplication
import com.jordiee.kotlindev.databinding.ActivityAddDataBinding
import com.jordiee.kotlindev.viewmodel.NotesViewModel
import com.jordiee.kotlindev.viewmodel.NotesViewModelFactory


class AddDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDataBinding
    private lateinit var notesViewModel : NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        notesViewModel = ViewModelProvider(this, NotesViewModelFactory((application as NotesApplication).notesRepo))[NotesViewModel::class.java]
        setContentView(binding.root)
    }

    fun submitData(view: View) {
      val intent = Intent()
        intent.putExtra("title", binding.etTitle.text.toString())
        intent.putExtra("description", binding.etDescription.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}
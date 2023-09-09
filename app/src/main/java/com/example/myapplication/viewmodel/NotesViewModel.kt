package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.myapplication.model.Note
import com.example.myapplication.model.NotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NotesViewModel(private val repo : NotesRepo) : ViewModel() {

    val myAllNotes : LiveData<List<Note>> = repo.allNotes.asLiveData()

    fun insertNote(note : Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(note)
        }
    }

    fun deleteNotes(note : Note) {
        viewModelScope.launch {
            repo.delete(note)
        }
    }

    fun deleteAllNotes(note: Note) {
        viewModelScope.launch {
            repo.deleteAllNotes()
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repo.update(note)
        }
    }

}
class NotesViewModelFactory(private val repo: NotesRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(repo) as T
        }
        throw Exception()
    }
}
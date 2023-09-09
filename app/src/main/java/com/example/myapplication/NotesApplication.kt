package com.example.myapplication

import android.app.Application
import com.example.myapplication.model.NotesDatabase
import com.example.myapplication.model.NotesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NotesApplication : Application() {
    val notesDb by lazy { NotesDatabase.getInstance(this, CoroutineScope(SupervisorJob())) }
    val notesRepo by lazy { NotesRepo(notesDb.getNoteDao()) }
}
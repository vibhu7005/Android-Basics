package com.jordiee.kotlindev

import android.app.Application
import com.jordiee.kotlindev.model.NotesDatabase
import com.jordiee.kotlindev.model.NotesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NotesApplication : Application() {
    val notesDb by lazy { NotesDatabase.getInstance(this, CoroutineScope(SupervisorJob())) }
    val notesRepo by lazy { NotesRepo(notesDb.getNoteDao()) }
}
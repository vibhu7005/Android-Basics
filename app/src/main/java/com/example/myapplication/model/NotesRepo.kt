package com.example.myapplication.model

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NotesRepo(private val notesDao : NotesDao) {
    val allNotes : Flow<List<Note>> = notesDao.getAllNotes()

    @WorkerThread
    suspend fun insert(note : Note) {
        notesDao.insert(note)
    }

    @WorkerThread
    suspend fun update(note : Note) {
        notesDao.update(note)
    }

    @WorkerThread
    suspend fun delete(note : Note) {
        notesDao.delete(note)
    }

//    @WorkerThread
//    suspend fun deleteAllNotes() {
//        notesDao.deleteAllData()
//    }
}
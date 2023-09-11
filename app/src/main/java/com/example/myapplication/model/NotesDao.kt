package com.example.myapplication.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)  // or OnConflictStrategy.IGNORE

    suspend fun insert(note: Note)

    @Update
     suspend fun update(note : Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM NOTE_TABLE ORDER BY id ASC")
     fun getAllNotes() : Flow<List<Note>>

    @Query("DELETE from NOTE_TABLE")
      fun deleteAllData() : Int

}
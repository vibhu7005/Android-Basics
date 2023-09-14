package com.jordiee.kotlindev.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    val title : String,
    val description : String)

package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(var title : String, var description : String) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 1
}
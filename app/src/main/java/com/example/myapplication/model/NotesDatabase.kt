package com.example.myapplication.model

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 10)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NotesDao

    companion object {
        @Volatile
        private var instance: NotesDatabase? = null
        fun getInstance(context: Context, coroutineScope: CoroutineScope): NotesDatabase {
            return instance?.let {
                return it
            } ?: synchronized(this) {
                val tempInstance =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "jordieeDb")
                        .addCallback(NotesDbCallBack(coroutineScope))
                        .build()
                instance = tempInstance
                return tempInstance
            }
        }
    }

    private class NotesDbCallBack(val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            scope.launch {
                val note1 = Note(title = "xyz", description = "abc")
                val note2 = Note(title = "ui", description = "ko")
                val note3 = Note(title = "ji", description = "oi")
                val note4 = Note(title = "89", description = "lop")

                instance?.getNoteDao()?.insert(note1)
                instance?.getNoteDao()?.insert(note2)
                instance?.getNoteDao()?.insert(note3)
                instance?.getNoteDao()?.insert(note4)

            }
        }
    }
}



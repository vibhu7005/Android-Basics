package com.jordiee.kotlindev.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
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

                        .build()
                instance = tempInstance
                return tempInstance
            }
        }
    }

}



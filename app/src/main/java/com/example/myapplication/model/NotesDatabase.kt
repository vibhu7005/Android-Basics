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

@Database(entities = [Note::class], version = 1)
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
                instance?.getNoteDao()?.insert(Note("work 1", "java collections"))
                instance?.getNoteDao()?.insert(Note("work 2", "Take Medicine"))
                instance?.getNoteDao()?.insert(Note("work 3", "Take a nap"))
            }
        }
    }
}



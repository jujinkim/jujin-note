package com.jujinkim.note.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jujinkim.note.data.dao.CategoryDao
import com.jujinkim.note.data.dao.NoteDao
import com.jujinkim.note.data.entity.Category
import com.jujinkim.note.data.entity.Note

@Database(entities = [Note::class, Category::class], version = 100)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
}
package com.jujinkim.note.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jujinkim.note.data.dao.CategoryDao
import com.jujinkim.note.data.dao.NoteDao
import com.jujinkim.note.data.entity.CategoryEntity
import com.jujinkim.note.data.entity.NoteEntity

@Database(entities = [NoteEntity::class, CategoryEntity::class], version = 100)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        const val DB_NAME = "jujin-note-db"
    }
}
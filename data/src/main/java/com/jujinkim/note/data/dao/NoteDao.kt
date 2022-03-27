package com.jujinkim.note.data.dao

import androidx.room.*
import com.jujinkim.note.data.entity.Note

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Insert
    fun insertAll(vararg notes: Note)

    @Delete
    fun delete(note: Note)

    @Delete
    fun deleteAll(vararg notes: Note)

    @Delete
    fun deleteAll()

    @Update
    fun update(note: Note)

    @Update
    fun updateAll(vararg notes: Note)

    @Query("SELECT * FROM Note WHERE id = :id")
    fun get(id: String): Note

    @Query("SELECT * FROM Note WHERE category_id = :catId")
    fun getAllByCategory(catId: String): List<Note>

    @Query("SELECT * FROM Note")
    fun getAll(): List<Note>
}
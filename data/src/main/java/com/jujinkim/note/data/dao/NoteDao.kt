package com.jujinkim.note.data.dao

import androidx.room.*
import com.jujinkim.note.data.entity.NoteEntity

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteEntity: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg noteEntities: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Query("DELETE FROM note WHERE id = :id")
    fun delete(id: String)

    @Delete
    fun deleteAll(vararg noteEntities: NoteEntity)

    @Update
    fun update(noteEntity: NoteEntity)

    @Update
    fun updateAll(vararg noteEntities: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    fun get(id: String): NoteEntity

    @Query("SELECT * FROM note WHERE category_id = :catId")
    fun getAllByCategory(catId: String): List<NoteEntity>

    @Query("SELECT * FROM note")
    fun getAll(): List<NoteEntity>
}
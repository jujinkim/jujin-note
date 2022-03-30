package com.jujinkim.note.data.repo

import com.jujinkim.note.data.repo.datasource.NoteDataSource
import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category

interface NoteRepo {
    suspend fun getNote(id: String): Note
    suspend fun getNotes(catId: String): List<Note>
    suspend fun getAllNotes(): List<Note>
    suspend fun getCategory(catId: String): Category
    suspend fun getCategories(): List<Category>

    suspend fun saveNote(note: Note, isNew: Boolean = true)
    suspend fun saveNotes(notes: List<Note>, isNew: Boolean = true)
    suspend fun saveCategory(category: Category, isNew: Boolean = true)
    suspend fun saveCategories(categories: List<Category>, isNew: Boolean = true)

    suspend fun deleteNote(note: Note)
    suspend fun deleteNote(id: String)
    suspend fun deleteNotes(notes: List<Note>)
    suspend fun deleteCategory(category: Category)
    suspend fun deleteCategory(catId: String)

    suspend fun mirrorNotes(source: List<Note>)
    suspend fun mirrorCategories(source: List<Category>)

    fun getCurrentDataSource(): NoteDataSource
}
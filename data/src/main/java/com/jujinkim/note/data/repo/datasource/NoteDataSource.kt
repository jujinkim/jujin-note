package com.jujinkim.note.data.repo.datasource

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category

interface NoteDataSource {
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
    suspend fun deleteAllNotes()
    suspend fun deleteAllCategories()
}
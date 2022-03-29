package com.jujinkim.note.data.repo

import com.jujinkim.note.data.repo.datasource.NoteDataSource
import com.jujinkim.note.model.Note
import com.jujinkim.note.model.NoteCategory

interface NoteRepo {
    fun getNote(id: String): Note
    fun getNotes(catId: String): List<Note>
    fun getAllNotes(): List<Note>
    fun getCategory(catId: String): NoteCategory
    fun getCategories(): List<NoteCategory>

    fun saveNote(note: Note, isNew: Boolean = true)
    fun saveNotes(notes: List<Note>, isNew: Boolean = true)
    fun saveCategory(category: NoteCategory, isNew: Boolean = true)
    fun saveCategories(categories: List<NoteCategory>, isNew: Boolean = true)

    fun deleteNote(note: Note)
    fun deleteNote(id: String)
    fun deleteNotes(notes: List<Note>)
    fun deleteCategory(category: NoteCategory)
    fun deleteCategory(catId: String)

    fun mirrorNotes(source: List<Note>)
    fun mirrorCategories(source: List<NoteCategory>)

    fun getCurrentDataSource(): NoteDataSource
}
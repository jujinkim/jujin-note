package com.jujinkim.note.data.repo

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.NoteCategory

interface NoteRepo {
    fun getNote(id: String): Note
    fun getNotes(catId: String): List<Note>
    fun getAllNotes(): List<Note>
    fun getCategory(catId: String): NoteCategory
    fun getCategories(): List<NoteCategory>
}
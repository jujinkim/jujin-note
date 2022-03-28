package com.jujinkim.note.data.repo

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.NoteCategory

class NoteRepoImpl: NoteRepo {
    override fun getNote(id: String): Note {
        TODO("Not yet implemented")
    }

    override fun getNotes(catId: String): List<Note> {
        TODO("Not yet implemented")
    }

    override fun getAllNotes(): List<Note> {
        TODO("Not yet implemented")
    }

    override fun getCategory(catId: String): NoteCategory {
        TODO("Not yet implemented")
    }

    override fun getCategories(): List<NoteCategory> {
        TODO("Not yet implemented")
    }

    override fun saveNote(note: Note, isNew: Boolean) {
        TODO("Not yet implemented")
    }

    override fun saveNotes(notes: List<Note>, isNew: Boolean) {
        TODO("Not yet implemented")
    }

    override fun saveCategory(category: NoteCategory, isNew: Boolean) {
        TODO("Not yet implemented")
    }

    override fun saveCategories(categories: List<NoteCategory>, isNew: Boolean) {
        TODO("Not yet implemented")
    }
}
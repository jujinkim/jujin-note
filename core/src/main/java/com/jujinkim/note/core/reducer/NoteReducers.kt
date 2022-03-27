package com.jujinkim.note.core.reducer

import com.jujinkim.note.core.AppState
import com.jujinkim.note.data.repo.NoteRepo
import com.jujinkim.note.model.Note
import com.jujinkim.note.model.NoteCategory
import javax.inject.Inject

object NoteReducers {
    @set:Inject
    lateinit var noteRepo: NoteRepo

    fun addNote(state: AppState, note: Note) = state.copy().apply {
        notes.getOrPut(note.categoryId) { mutableListOf() }.add(note)
    }

    fun removeNote(state: AppState, note: Note) = state.copy().apply {
        notes[note.categoryId]?.remove(note)
    }

    fun removeNotes(state: AppState, notes: List<Note>) = state.copy().apply {
        notes.forEach { note ->
            this.notes[note.categoryId]?.remove(note)
        }
    }

    fun addCategory(state: AppState, category: NoteCategory) = state.copy().apply {
        notes[category.id] = mutableListOf()
        categories.add(category)
    }

    fun removeCategory(state: AppState, category: NoteCategory) = state.copy().apply {
        notes.remove(category.id)
        categories.remove(category)
    }

    fun loadCategory(state: AppState) = state.copy(
        categories = noteRepo.getCategories().toMutableList()
    )

    fun loadNotes(state: AppState, category: NoteCategory) = state.copy().apply {
        notes[category.id] = noteRepo.getNotes(category.id).toMutableList()
    }

    fun checkNoteHasExpired(state: AppState, note: Note) = state.copy().apply {
        if (note.expiredTime > System.currentTimeMillis()) {
            notes[note.categoryId]?.remove(note)
        }
    }
}

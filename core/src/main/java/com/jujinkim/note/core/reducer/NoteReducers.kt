package com.jujinkim.note.core.reducer

import com.jujinkim.note.core.AppState
import com.jujinkim.note.data.repo.NoteRepo
import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category

object NoteReducers {
    fun addNote(state: AppState, note: Note, noteRepo: NoteRepo) = state.copy().apply {
        notes.getOrPut(note.categoryId) { mutableListOf() }.add(note)
    }.also {
        noteRepo.saveNote(note)
    }

    fun removeNote(state: AppState, note: Note, noteRepo: NoteRepo) = state.copy().apply {
        notes[note.categoryId]?.remove(note)
    }.also {
        noteRepo.deleteNote(note)
    }

    fun removeNotes(state: AppState, notes: List<Note>, noteRepo: NoteRepo) = state.copy().apply {
        notes.forEach { note ->
            this.notes[note.categoryId]?.remove(note)
        }
    }.also {
        noteRepo.deleteNotes(notes)
    }

    fun addCategory(state: AppState, category: Category, noteRepo: NoteRepo) = state.copy().apply {
        notes[category.id] = mutableListOf()
        categories.add(category)
    }.also {
        noteRepo.saveCategory(category)
    }

    fun removeCategory(state: AppState, category: Category, noteRepo: NoteRepo) = state.copy().apply {
        notes.remove(category.id)
        categories.remove(category)
    }.also {
        noteRepo.deleteCategory(category)
    }

    fun loadCategory(state: AppState, noteRepo: NoteRepo) = state.copy(
        categories = noteRepo.getCategories().toMutableList()
    )

    fun loadNotes(state: AppState, category: Category, noteRepo: NoteRepo) = state.copy().apply {
        notes[category.id] = noteRepo.getNotes(category.id).toMutableList()
    }

    fun checkNoteHasExpired(state: AppState, note: Note, noteRepo: NoteRepo) = state.copy().apply {
        if (note.isExpired()) {
            notes[note.categoryId]?.remove(note)
        }
    }.also {
        noteRepo.deleteNote(note.id)
    }

    fun checkAllNoteExpiredAndUpdate(state: AppState, noteRepo: NoteRepo) = state.copy().apply {
        val removedList = mutableListOf<Note>()
        notes.forEach {
            it.value.removeAll {
                    note -> if (note.isExpired()) { removedList.add(note); true } else { false }
            }
        }
        noteRepo.deleteNotes(removedList)
    }
}

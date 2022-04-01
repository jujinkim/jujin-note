package com.jujinkim.note.core.reducer

import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.NoteRepoLoadItemType
import com.jujinkim.note.core.Notes
import com.jujinkim.note.data.repo.NoteRepo
import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category
import kotlinx.coroutines.*

object NoteReducers {
    private val noteReducerScope = CoroutineScope(Dispatchers.IO)

    fun addNote(state: AppState, note: Note, noteRepo: NoteRepo) = state.copy().apply {
        notes.getOrPut(note.categoryId) { mutableListOf() }.add(note)
    }.also {
        noteReducerScope.launch {
            noteRepo.saveNote(note)
        }
    }

    fun removeNote(state: AppState, note: Note, noteRepo: NoteRepo) = state.copy().apply {
        notes[note.categoryId]?.remove(note)
    }.also {
        noteReducerScope.launch {
            noteRepo.deleteNote(note)
        }
    }

    fun removeNotes(state: AppState, notes: List<Note>, noteRepo: NoteRepo) = state.copy().apply {
        notes.forEach { note ->
            this.notes[note.categoryId]?.remove(note)
        }
    }.also {
        noteReducerScope.launch {
            noteRepo.deleteNotes(notes)
        }
    }

    fun addCategory(state: AppState, category: Category, noteRepo: NoteRepo) = state.copy().apply {
        notes[category.id] = mutableListOf()
        categories.add(category)
    }.also {
        noteReducerScope.launch {
            noteRepo.saveCategory(category)
        }
    }

    fun removeCategory(state: AppState, category: Category, noteRepo: NoteRepo) = state.copy().apply {
        notes.remove(category.id)
        categories.remove(category)
    }.also {
        noteReducerScope.launch {
            noteRepo.deleteCategory(category)
        }
    }

    fun checkNoteHasExpired(state: AppState, note: Note, noteRepo: NoteRepo) = state.copy().apply {
        if (note.isExpired()) {
            notes[note.categoryId]?.remove(note)
        }
    }.also {
        noteReducerScope.launch {
            noteRepo.deleteNote(note.id)
        }
    }

    fun checkAllNoteExpiredAndUpdate(state: AppState, noteRepo: NoteRepo) = state.copy().apply {
        val removedList = mutableListOf<Note>()
        notes.forEach {
            it.value.removeAll {
                    note -> if (note.isExpired()) { removedList.add(note); true } else { false }
            }
        }
        noteReducerScope.launch {
            noteRepo.deleteNotes(removedList)
        }
    }

    fun getFromDbStart(state: AppState) = state.copy()

    @Suppress("UNCHECKED_CAST")
    fun getFromDbSuccess(state: AppState, data: Any?, type: NoteRepoLoadItemType) = state.copy().apply {
        when(type) {
            NoteRepoLoadItemType.NOTES -> {
                (data as Notes).forEach { note ->
                    val catId = note.categoryId
                    notes.getOrPut(catId) { mutableListOf() }.add(note)
                }
            }
            NoteRepoLoadItemType.CATEGORIES -> {
                categories.clear()
                categories.addAll(data as MutableList<Category>)
            }
        }
    }

    fun getFromDbFailed(state: AppState, msg: String) = state.copy()
}

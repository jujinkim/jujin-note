package com.jujinkim.note.core.reducer

import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.NoteRepoLoadItemType
import com.jujinkim.note.core.Notes
import com.jujinkim.note.data.AppSharedPref
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

    fun updateNote(state: AppState, note: Note, noteRepo: NoteRepo) = state.copy().apply {
        val idx = notes.getOrPut(note.categoryId) { mutableListOf() }.indexOfFirst { it.id == note.id }
        if (idx < 0) {
            notes.getOrPut(note.categoryId) { mutableListOf() }.add(note)
        } else {
            notes.getOrPut(note.categoryId) { mutableListOf() }[idx] = note
        }
    }.also {
        noteReducerScope.launch {
            noteRepo.saveNote(note, false)
        }
    }

    fun deleteNote(state: AppState, note: Note, noteRepo: NoteRepo) = state.copy().apply {
        notes[note.categoryId]?.remove(note)
    }.also {
        noteReducerScope.launch {
            noteRepo.deleteNote(note)
        }
    }

    fun deleteNotes(state: AppState, notes: List<Note>, noteRepo: NoteRepo) = state.copy().apply {
        notes.toList().forEach { note ->
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

    fun updateCategory(state: AppState, category: Category, noteRepo: NoteRepo) = state.copy().apply {
        categories[categories.indexOfFirst { it.id == category.id }] = category
    }.also {
        noteReducerScope.launch {
            noteRepo.saveCategory(category, false)
        }
    }

    fun deleteCategory(state: AppState, category: Category, noteRepo: NoteRepo) = state.copy().apply {
        notes.remove(category.id)
        categories.remove(category)
    }.also {
        noteReducerScope.launch {
            noteRepo.deleteCategory(category)
        }
    }

    fun updateDraftNote(
        state: AppState,
        categoryId: String,
        text: String,
        sharedPref: AppSharedPref
    ) = state.copy().apply {
        draftNotes[categoryId] = text
        sharedPref.saveDraftNote(categoryId, text)
    }

    fun getAllDraftNotes(
        state: AppState,
        sharedPref: AppSharedPref
    ) = state.copy(
        draftNotes = HashMap<String, String>().apply {
            sharedPref.getAllDraftNotes().forEach {
                put(it.key, it.value)
            }
        }
    )

    fun checkNoteHasExpired(state: AppState, note: Note, noteRepo: NoteRepo) = state.copy().apply {
        if (note.isExpired()) {
            notes[note.categoryId]?.remove(note)
        }
    }.also {
        noteReducerScope.launch {
            noteRepo.deleteNote(note.id)
        }
    }

    fun checkAllNoteInvalidAndUpdate(state: AppState, noteRepo: NoteRepo) = state.copy().apply {
        val removedList = mutableListOf<Note>()

        notes.forEach {
            // expired
            it.value.removeAll {
                note -> if (note.isExpired()) { removedList.add(note); true } else { false }
            }

            // empty notes
            it.value.removeAll {
                note -> if (note.content.isBlank()) { removedList.add(note); true } else { false }
            }
        }

        noteReducerScope.launch {
            noteRepo.deleteNotes(removedList)
        }
    }

    fun getFromDbStart(state: AppState, type: NoteRepoLoadItemType) = state.copy().apply {
        isDbLoading[type] = true
    }

    @Suppress("UNCHECKED_CAST")
    fun getFromDbSuccess(state: AppState, data: Any?, type: NoteRepoLoadItemType) = state.copy().apply {
        when(type) {
            NoteRepoLoadItemType.NOTES -> {
                notes.clear()
                (data as Notes).forEach { note ->
                    val catId = note.categoryId
                    notes.getOrPut(catId) { mutableListOf() }.apply {
                        add(note)
                    }
                }
            }
            NoteRepoLoadItemType.CATEGORIES -> {
                categories.clear()
                categories.addAll(data as MutableList<Category>)
            }
            else -> {}
        }
        isDbLoading[type] = false
    }

    fun getFromDbFailed(state: AppState, msg: String, type: NoteRepoLoadItemType) = state.copy().apply {
        isDbLoading[type] = false
    }
}

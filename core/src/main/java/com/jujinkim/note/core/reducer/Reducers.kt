package com.jujinkim.note.core.reducer

import com.jujinkim.note.core.*

fun rootReducer(state: AppState, action: Any) =
    when (action) {
        is NoteAction -> notesReducer(state, action)
        is SettingAction -> state   // not implemented
        else -> state
    }

fun notesReducer(state: AppState, action: NoteAction) =
    when (action) {
        is AddNote -> NoteReducers().addNote(state, action.note)
        is RemoveNote -> NoteReducers().removeNote(state, action.note)
        is RemoveNotes -> NoteReducers().removeNotes(state, action.notes)
        is AddCategory -> NoteReducers().addCategory(state, action.category)
        is RemoveCategory -> NoteReducers().removeCategory(state, action.category)
        is LoadCategories -> NoteReducers().loadCategory(state)
        is LoadNotes -> NoteReducers().loadNotes(state, action.category)
        is CheckNoteHasExpired -> NoteReducers().checkNoteHasExpired(state, action.note)
        else -> state
    }
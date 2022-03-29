package com.jujinkim.note.core.reducer

import com.jujinkim.note.core.*
import com.jujinkim.note.data.repo.NoteRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppReducer @Inject constructor(
    private val noteRepo: NoteRepo
) {
    fun rootReducer(state: AppState, action: Any) =
        when (action) {
            is NoteAction -> notesReducer(state, action)
            is SettingAction -> state   // not implemented
            else -> state
        }

    private fun notesReducer(state: AppState, action: NoteAction) =
        when (action) {
            is AddNote -> NoteReducers.addNote(state, action.note)
            is RemoveNote -> NoteReducers.removeNote(state, action.note)
            is RemoveNotes -> NoteReducers.removeNotes(state, action.notes)
            is AddCategory -> NoteReducers.addCategory(state, action.category)
            is RemoveCategory -> NoteReducers.removeCategory(state, action.category)
            is LoadCategories -> NoteReducers.loadCategory(state, noteRepo)
            is LoadNotes -> NoteReducers.loadNotes(state, action.category, noteRepo)
            is CheckNoteHasExpired -> NoteReducers.checkNoteHasExpired(state, action.note)
        }
}

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
            is UiAction -> uiReducer(state, action)
            is SettingAction -> state   // not implemented
            else -> state
        }

    private fun uiReducer(state: AppState, action: UiAction) =
        when (action) {
            is UiAction.NavigateToCategories ->
                UiReducers.navigateToCategories(state)
            is UiAction.NavigateToNotes ->
                UiReducers.navigateToNotes(state, action.cat)
            is UiAction.NavigateToSettings ->
                UiReducers.navigateToSettings(state)
        }

    private fun notesReducer(state: AppState, action: NoteAction) =
        when (action) {
            is NoteAction.AddNote ->
                NoteReducers.addNote(state, action.note, noteRepo)
            is NoteAction.RemoveNote ->
                NoteReducers.removeNote(state, action.note, noteRepo)
            is NoteAction.RemoveNotes ->
                NoteReducers.removeNotes(state, action.notes, noteRepo)
            is NoteAction.AddCategory ->
                NoteReducers.addCategory(state, action.category, noteRepo)
            is NoteAction.RemoveCategory ->
                NoteReducers.removeCategory(state, action.category, noteRepo)
            is NoteAction.CheckNoteHasExpiredAndUpdate ->
                NoteReducers.checkNoteHasExpired(state, action.note, noteRepo)
            is NoteAction.CheckAllNoteExpiredAndUpdate ->
                NoteReducers.checkAllNoteExpiredAndUpdate(state, noteRepo)
            is NoteAction.GetFromDbStart ->
                NoteReducers.getFromDbStart(state)
            is NoteAction.GetFromDbSuccess ->
                NoteReducers.getFromDbSuccess(state, action.data, action.type)
            is NoteAction.GetFromDbFailed ->
                NoteReducers.getFromDbFailed(state, action.msg)
        }
}

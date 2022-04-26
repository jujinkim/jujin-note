package com.jujinkim.note.core.reducer

import android.content.Context
import com.jujinkim.note.core.*
import com.jujinkim.note.data.repo.NoteRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppReducer @Inject constructor(
    private val noteRepo: NoteRepo,
    @ApplicationContext private val context: Context
) {
    fun rootReducer(state: AppState, action: Any) =
        when (action) {
            is NoteAction -> notesReducer(state, action)
            is UiAction -> uiReducer(state, action)
            is SettingAction -> settingReducer(state, action)   // not implemented
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
            is UiAction.ToggleCategoryEditMode ->
                UiReducers.toggleCategoryEditMode(state)
        }

    private fun notesReducer(state: AppState, action: NoteAction) =
        when (action) {
            is NoteAction.AddNote ->
                NoteReducers.addNote(state, action.note, noteRepo)
            is NoteAction.UpdateNote ->
                NoteReducers.updateNote(state, action.note, noteRepo)
            is NoteAction.DeleteNote ->
                NoteReducers.deleteNote(state, action.note, noteRepo)
            is NoteAction.DeleteNotes ->
                NoteReducers.deleteNotes(state, action.notes, noteRepo)
            is NoteAction.AddCategory ->
                NoteReducers.addCategory(state, action.category, noteRepo)
            is NoteAction.UpdateCategory ->
                NoteReducers.updateCategory(state, action.category, noteRepo)
            is NoteAction.DeleteCategory -> {
                val tmpState = NoteReducers.deleteNotes(state, action.relatedNotes, noteRepo)
                NoteReducers.deleteCategory(tmpState, action.category, noteRepo)
            }
            is NoteAction.CheckNoteHasExpiredAndUpdate ->
                NoteReducers.checkNoteHasExpired(state, action.note, noteRepo)
            is NoteAction.CheckAllNoteInvalidAndUpdate ->
                NoteReducers.checkAllNoteInvalidAndUpdate(state, noteRepo)
            is NoteAction.GetFromDbStart ->
                NoteReducers.getFromDbStart(state)
            is NoteAction.GetFromDbSuccess ->
                NoteReducers.getFromDbSuccess(state, action.data, action.type)
            is NoteAction.GetFromDbFailed ->
                NoteReducers.getFromDbFailed(state, action.msg)
        }

    private fun settingReducer(state: AppState, action: SettingAction) =
        when (action) {
            is SettingAction.LoadSetting -> SettingReducers.loadSetting(state, context)
            is SettingAction.SaveSetting -> SettingReducers.saveSetting(state, context, action.setting)
            is SettingAction.SyncNotesRemote -> TODO()
        }
}

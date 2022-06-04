package com.jujinkim.note.core

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category
import com.jujinkim.note.model.Setting

sealed class UiAction {
    object NavigateToCategories: UiAction()
    data class NavigateToNotes(val cat: Category): UiAction()
    object NavigateToSettings: UiAction()
    object ToggleCategoryEditMode: UiAction()
}

sealed class NoteAction {
    class AddNote(val note: Note): NoteAction()
    class UpdateNote(val note: Note): NoteAction()
    class DeleteNote(val note: Note): NoteAction()
    class DeleteNotes(val notes: List<Note>): NoteAction()
    class AddCategory(val category: Category): NoteAction()
    class UpdateCategory(val category: Category): NoteAction()
    class DeleteCategory(val category: Category, val relatedNotes: List<Note>): NoteAction()
    class UpdateDraftNote(val categoryId: String, val text: String): NoteAction()
    object GetAllDraftNotes: NoteAction()
    class CheckNoteHasExpiredAndUpdate(val note: Note): NoteAction()
    object CheckAllNoteInvalidAndUpdate: NoteAction()

    data class GetFromDbStart(val type: NoteRepoLoadItemType): NoteAction()
    data class GetFromDbSuccess(val data: Any?, val type: NoteRepoLoadItemType): NoteAction()
    data class GetFromDbFailed(val msg: String, val type: NoteRepoLoadItemType): NoteAction()
}

sealed class SettingAction {
    object LoadSetting: SettingAction()
    data class SaveSetting(val setting: Setting): SettingAction()
    object SyncNotesRemote: SettingAction()

}
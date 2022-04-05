package com.jujinkim.note.core

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category

sealed class UiAction {
    object NavigateToCategories: UiAction()
    data class NavigateToNotes(val cat: Category): UiAction()
    object NavigateToSettings: UiAction()
    object ToggleCategoryEditMode: UiAction()
}

sealed class NoteAction {
    class AddNote(val note: Note): NoteAction()
    class RemoveNote(val note: Note): NoteAction()
    class RemoveNotes(val notes: List<Note>): NoteAction()
    class AddCategory(val category: Category): NoteAction()
    class UpdateCategory(val category: Category): NoteAction()
    class RemoveCategory(val category: Category): NoteAction()
    class CheckNoteHasExpiredAndUpdate(val note: Note): NoteAction()
    object CheckAllNoteExpiredAndUpdate: NoteAction()

    object GetFromDbStart: NoteAction()
    data class GetFromDbSuccess(val data: Any?, val type: NoteRepoLoadItemType): NoteAction()
    data class GetFromDbFailed(val msg: String): NoteAction()
}

sealed class SettingAction {
    object SaveSetting: SettingAction()
    object LoadSetting: SettingAction()
    object SyncNotesRemote: SettingAction()

}
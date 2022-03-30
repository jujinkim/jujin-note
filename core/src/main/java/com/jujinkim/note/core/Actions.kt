package com.jujinkim.note.core

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category

sealed class NoteAction {
    class AddNote(val note: Note): NoteAction()
    class RemoveNote(val note: Note): NoteAction()
    class RemoveNotes(val notes: List<Note>): NoteAction()
    class AddCategory(val category: Category): NoteAction()
    class RemoveCategory(val category: Category): NoteAction()
    object LoadCategories : NoteAction()
    class LoadNotes(val category: Category): NoteAction()
    class CheckNoteHasExpiredAndUpdate(val note: Note): NoteAction()
    object CheckAllNoteExpiredAndUpdate: NoteAction()
}

sealed class SettingAction {
    object SaveSetting: SettingAction()
    object LoadSetting: SettingAction()
    object SyncNotesRemote: SettingAction()

}
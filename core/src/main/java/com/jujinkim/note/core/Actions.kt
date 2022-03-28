package com.jujinkim.note.core

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.NoteCategory

class AddNote(val note: Note): NoteAction()
class RemoveNote(val note: Note): NoteAction()
class RemoveNotes(val notes: List<Note>): NoteAction()
class AddCategory(val category: NoteCategory): NoteAction()
class RemoveCategory(val category: NoteCategory): NoteAction()
object LoadCategories : NoteAction()
class LoadNotes(val category: NoteCategory): NoteAction()
class CheckNoteHasExpired(val note: Note): NoteAction()

object SaveSetting: SettingAction()
object LoadSetting: SettingAction()
object SyncNotesRemote: SettingAction()

sealed class NoteAction
sealed class SettingAction
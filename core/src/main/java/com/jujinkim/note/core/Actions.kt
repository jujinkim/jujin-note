package com.jujinkim.note.core

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.NoteCategory

class AddNote(val note: Note): NoteAction()
class RemoveNote(val note: Note): NoteAction()
class RemoveNotes(val notes: List<Note>): NoteAction()
class AddCategory(val category: NoteCategory): NoteAction()
class RemoveCategory(val category: NoteCategory): NoteAction()
class LoadCategories: NoteAction()
class LoadNotes(val category: NoteCategory): NoteAction()
class CheckNoteHasExpired(val note: Note): NoteAction()

class SaveSetting: SettingAction()
class LoadSetting: SettingAction()
class SyncNotesRemote: SettingAction()

abstract class NoteAction
abstract class SettingAction
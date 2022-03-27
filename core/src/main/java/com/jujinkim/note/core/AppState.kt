package com.jujinkim.note.core

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.NoteCategory

typealias CategoryId = String
typealias Notes = MutableList<Note>

data class AppState (
    // note state
    val categories: MutableList<NoteCategory> = mutableListOf(),
    val notes: HashMap<CategoryId, Notes> = hashMapOf(),

    // view state
    val focusedCategoryId: CategoryId = ""
)
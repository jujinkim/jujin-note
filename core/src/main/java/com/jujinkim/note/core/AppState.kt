package com.jujinkim.note.core

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category

typealias CategoryId = String
typealias Notes = MutableList<Note>

data class AppState (
    // note state
    val categories: MutableList<Category> = mutableListOf(),
    val notes: HashMap<CategoryId, Notes> = hashMapOf(),

    // view state
    val focusedCategoryId: CategoryId = "",
    val currentScreen: AppScreen = AppScreen.CATEGORY_LIST
)

enum class AppScreen {
    CATEGORY_LIST,
    NOTE_LIST,
    SETTING
}
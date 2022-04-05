package com.jujinkim.note.core

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category

typealias CategoryId = String
typealias Notes = MutableList<Note>

data class AppState (
    // note state
    val categories: MutableList<Category> = mutableListOf(),
    val notes: HashMap<CategoryId, Notes> = hashMapOf(),

    // category list
    val isCategoryEditMode: Boolean = false,

    // view state
    val focusedCategory: Category = Category.new(""),
    val currentScreen: AppScreen = AppScreen.CATEGORY_LIST
) {
    val categoryId: String get() = focusedCategory.id
    val categoryTitle: String get() = focusedCategory.title
}

enum class AppScreen {
    CATEGORY_LIST,
    NOTE_LIST,
    SETTING
}
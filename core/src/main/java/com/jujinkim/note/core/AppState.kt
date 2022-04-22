package com.jujinkim.note.core

import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category
import com.jujinkim.note.model.Setting

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
    val currentScreen: AppScreen = AppScreen.CATEGORY_LIST,

    // setting
    val setting: Setting = Setting()
) {
    val categoryId: String get() = focusedCategory.id
    val categoryTitle: String get() = focusedCategory.title
}

enum class AppScreen {
    CATEGORY_LIST,
    NOTE_LIST,
    SETTING
}
package com.jujinkim.note.core.reducer

import com.jujinkim.note.core.AppScreen
import com.jujinkim.note.core.AppState
import com.jujinkim.note.model.Category

object UiReducers {
    fun navigateToCategories(state: AppState) = state.copy(
        currentScreen = AppScreen.CATEGORY_LIST
    )

    fun navigateToNotes(state: AppState, cat: Category) = state.copy(
        currentScreen = AppScreen.NOTE_LIST,
        focusedCategory = cat
    )

    fun navigateToSettings(state: AppState) = state.copy(
        currentScreen = AppScreen.SETTING
    )

    fun toggleCategoryEditMode(state: AppState) = state.copy(
        isCategoryEditMode = !state.isCategoryEditMode
    )
}
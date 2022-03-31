package com.jujinkim.note.core.reducer

import com.jujinkim.note.core.AppScreen
import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.CategoryId

object UiReducers {
    fun navigateToCategories(state: AppState) = state.copy(
        currentScreen = AppScreen.CATEGORY_LIST
    )

    fun navigateToNotes(state: AppState, catId: CategoryId) = state.copy(
        currentScreen = AppScreen.NOTE_LIST,
        focusedCategoryId = catId
    )

    fun navigateToSettings(state: AppState) = state.copy(
        currentScreen = AppScreen.SETTING
    )
}
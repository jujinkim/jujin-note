package com.jujinkim.note.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.core.AppScreen
import com.jujinkim.note.ui.categorylist.NoteCategoryListContent
import com.jujinkim.note.ui.notelist.NoteListContent

@Composable
fun MainActivityPhoneContent(viewModel: MainViewModel = hiltViewModel()) {
    when (viewModel.currentScreen) {
        AppScreen.CATEGORY_LIST -> NoteCategoryListContent()
        AppScreen.NOTE_LIST -> NoteListContent()
        AppScreen.SETTING -> TODO()
    }
}

@Composable
fun MainActivityTabletContent(viewModel: MainViewModel = hiltViewModel()) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        NoteCategoryListContent()

        when (viewModel.currentScreen) {
            AppScreen.CATEGORY_LIST, AppScreen.NOTE_LIST -> NoteListContent()
            AppScreen.SETTING -> TODO()
        }
    }
}
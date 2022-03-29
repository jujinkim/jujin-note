package com.jujinkim.note.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.jujinkim.note.core.AppScreen
import com.jujinkim.note.ui.categorylist.NoteCategoryListContent
import com.jujinkim.note.ui.notelist.NoteListContent

@Composable
fun MainActivityPhoneContent() {
    when (localMainViewModel.current.currentScreen) {
        AppScreen.CATEGORY_LIST -> NoteCategoryListContent()
        AppScreen.NOTE_LIST -> NoteListContent()
        AppScreen.SETTING -> TODO()
    }
}

@Composable
fun MainActivityTabletContent() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        NoteCategoryListContent()

        when (localMainViewModel.current.currentScreen) {
            AppScreen.CATEGORY_LIST, AppScreen.NOTE_LIST -> NoteListContent()
            AppScreen.SETTING -> TODO()
        }
    }
}
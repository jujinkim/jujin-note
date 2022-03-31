package com.jujinkim.note.ui

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.core.AppScreen
import com.jujinkim.note.ui.categorylist.CategoryListContent
import com.jujinkim.note.ui.notelist.NoteListContent

@Composable
fun MainActivityPhoneContent(viewModel: MainViewModel = hiltViewModel()) {
    when (viewModel.currentScreen) {
        AppScreen.CATEGORY_LIST -> CategoryListContent()
        AppScreen.NOTE_LIST -> NoteListContent()
        AppScreen.SETTING -> TODO()
    }
}

@Composable
fun MainActivityTabletContent(viewModel: MainViewModel = hiltViewModel()) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(0.45f)) {
            CategoryListContent()
        }
        Column(modifier = Modifier.weight(0.55f)) {
            when (viewModel.currentScreen) {
                AppScreen.CATEGORY_LIST, AppScreen.NOTE_LIST -> NoteListContent()
                AppScreen.SETTING -> TODO()
            }
        }
    }
}
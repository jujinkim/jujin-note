package com.jujinkim.note.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.core.AppScreen
import com.jujinkim.note.ui.categorylist.CategoryListContent
import com.jujinkim.note.ui.notelist.NoteListContent

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainActivityPhoneContent(viewModel: MainViewModel = hiltViewModel()) {
    AnimatedContent(targetState = viewModel.currentScreen) { currentScreen ->
        when (currentScreen) {
            AppScreen.CATEGORY_LIST -> CategoryListContent()
            AppScreen.NOTE_LIST -> NoteListContent()
            AppScreen.SETTING -> TODO()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainActivityTabletContent(viewModel: MainViewModel = hiltViewModel()) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(0.45f)) {
            CategoryListContent()
        }
        Column(modifier = Modifier.weight(0.55f)) {
            AnimatedContent(targetState = viewModel.currentScreen) { currentScreen ->
                when (currentScreen) {
                    AppScreen.CATEGORY_LIST, AppScreen.NOTE_LIST -> NoteListContent()
                    AppScreen.SETTING -> TODO()
                }
            }

        }
    }
}
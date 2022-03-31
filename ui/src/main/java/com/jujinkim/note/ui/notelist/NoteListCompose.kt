package com.jujinkim.note.ui.notelist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.ui.BackHandler
import com.jujinkim.note.ui.isWideScreen

@Composable
fun NoteListContent(viewModel: NoteListViewModel = hiltViewModel()) {
    if (!isWideScreen()) {
        BackHandler {
            viewModel.invokeBackToCategories()
        }
    }

    val notes = viewModel.notes
    val catId = viewModel.categoryId

    LazyColumn {
        items(notes) {
            NoteItemCompose(note = it)
        }
    }
}
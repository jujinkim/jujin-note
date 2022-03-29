package com.jujinkim.note.ui.notelist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NoteListContent(viewModel: NoteListViewModel = hiltViewModel()) {
    val notes = viewModel.notes

    LazyColumn {
        items(notes) {
            NoteItemCompose(note = it)
        }
    }
}
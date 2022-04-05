package com.jujinkim.note.ui.notelist

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.ui.BackHandler
import com.jujinkim.note.ui.R
import com.jujinkim.note.ui.isWideScreen

@Preview(showBackground = true)
@Composable
fun NoteListPreview() {
    NoteListContent()
}

@Composable
fun NoteListContent(viewModel: NoteListViewModel = hiltViewModel()) {
    val isWideScreen = isWideScreen()
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        if (!isWideScreen) {
            viewModel.invokeBackToCategories()
        } else {
            activity?.finish()
        }
    }

    Scaffold (
        topBar = { NoteListTopBar() }
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(viewModel.notes) {
                    NoteItemCompose(note = it)
                }
            }
            NoteInput { viewModel.invokeAddNote(it) }
        }
    }

}

@Composable
fun NoteListTopBar(viewModel: NoteListViewModel = hiltViewModel()) {
    val isWideScreen = isWideScreen()
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (!isWideScreen) {
            Button(onClick = { viewModel.invokeBackToCategories() }) {
                Text(text = "<")
            }
        }
        Text(text = viewModel.categoryName)
    }

}

@Composable
fun NoteInput(onNoteAddClick: (note: String) -> Unit) {
    val noteInput = remember { mutableStateOf("") }

    Row {
        TextField(modifier = Modifier.weight(1f), value = noteInput.value, onValueChange = { noteInput.value = it })
        Button(onClick = { onNoteAddClick(noteInput.value); noteInput.value = "" }) {
            Text(text = stringResource(R.string.add_note))
        }
    }
}
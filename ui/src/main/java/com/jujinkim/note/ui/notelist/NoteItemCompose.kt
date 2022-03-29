package com.jujinkim.note.ui.notelist

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jujinkim.note.model.Note

@Composable
fun NoteItemCompose(note: Note) {
    Text(text = note.content)
}
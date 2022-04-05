package com.jujinkim.note.ui.notelist

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jujinkim.note.model.Note

@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    NoteItemCompose(Note("testId", "testCatId", "content", 0))
}

@Composable
fun NoteItemCompose(note: Note) {
    Text(text = note.content)
}
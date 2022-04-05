package com.jujinkim.note.ui.notelist

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.jujinkim.note.model.Note
import com.jujinkim.note.ui.R
import com.jujinkim.note.util.Util

@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    NoteItemCompose(Note("testId", "testCatId", "content", 0))
}

@Composable
fun NoteItemCompose(note: Note) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .padding(6.dp)
    ) {
        Text(
            text = note.content,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = Util.msTimeToString(note.generatedTime),
                fontSize = 12.sp
            )
            Text(text = "~", fontSize = 12.sp)
            Text(
                text = if (note.expiredTime >= 0) Util.msTimeToString(note.expiredTime)
                else stringResource(id = R.string.expired_date_permanent),
                fontSize = 12.sp
            )
        }

    }
}
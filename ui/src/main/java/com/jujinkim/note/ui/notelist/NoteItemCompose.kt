package com.jujinkim.note.ui.notelist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import com.jujinkim.note.ui.theme.LocalColors
import com.jujinkim.note.util.Util

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    NoteItemCompose(Note("testId", "testCatId", "content", 0), {}, {})
}

@ExperimentalFoundationApi
@Composable
fun NoteItemCompose(
    note: Note,
    onClick: (note: Note) -> Unit,
    onLongClick: (note: Note) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .padding(6.dp)
            .combinedClickable(
                onClick = { onClick(note) },
                onLongClick = { onLongClick(note) }
            )
    ) {
        val textColor =
            if (note.isExpired()) LocalColors.current.textGrayed else LocalColors.current.onBackground
        Text(
            text = note.content,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp,
            color = textColor
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
        )
        Row(modifier = Modifier
            .wrapContentSize()
            .wrapContentHeight()
        ) {
            Text(
                text = Util.millisToDateTimeString(note.generatedTime),
                fontSize = 12.sp,
                color = textColor
            )
            Text(text = " ~ ", fontSize = 12.sp, color = textColor)
            Text(
                text = if (note.expiredTime >= 0) Util.millisToDateString(note.expiredTime)
                else stringResource(id = R.string.expired_date_permanent),
                fontSize = 12.sp,
                color = textColor
            )
        }

    }
}
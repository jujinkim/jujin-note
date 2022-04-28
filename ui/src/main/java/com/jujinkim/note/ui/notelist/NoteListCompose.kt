package com.jujinkim.note.ui.notelist

import android.app.Activity
import android.app.DatePickerDialog
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.model.Note
import com.jujinkim.note.ui.AppDialog
import com.jujinkim.note.ui.BackHandler
import com.jujinkim.note.ui.R
import com.jujinkim.note.ui.isWideScreen
import com.jujinkim.note.util.Util
import kotlinx.coroutines.delay
import java.util.*

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun NoteListPreview() {
    NoteListContent()
}

@ExperimentalFoundationApi
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

    val isShowOptionDialog = remember { mutableStateOf(false) }
    val selectedNoteForDialog = remember { mutableStateOf(Note.new("", "")) }

    Scaffold (
        topBar = { NoteListTopBar() }
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(viewModel.notes) { note ->
                    NoteItemCompose(
                        note = note
                    ) { isShowOptionDialog.value = true; selectedNoteForDialog.value = note }
                }
            }
            NoteInput { viewModel.invokeAddNote(it) }
        }
    }

    NoteOptionDialog(
        isShowDialog = isShowOptionDialog.value,
        note = selectedNoteForDialog. value,
        onDismiss = { isShowOptionDialog.value = false }
    )
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

@Composable
fun NoteOptionDialog(isShowDialog: Boolean, note: Note, onDismiss: () -> Unit) {
    var deleteNoteTimerCount by remember { mutableStateOf(0) }
    val dismissDialog = { deleteNoteTimerCount = 0; onDismiss() }

    var newExpiredDate by remember { mutableStateOf(note.expiredTime) }
    val calNow = Calendar.getInstance().apply {
        if (newExpiredDate >= 0) timeInMillis = newExpiredDate
    }
    val context = LocalContext.current


    val viewModel: NoteListViewModel = hiltViewModel()
    AppDialog(isShowDialog = isShowDialog, onDismiss = dismissDialog) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Content
            Text(
                text = note.content,
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )
            // Generated at
            Text(
                text = stringResource(
                    R.string.note_generated_at_ps,
                    Util.millisToDateTimeString(note.generatedTime)
                ),
            )
            // Expired at
            Row {
                Text(
                    text = stringResource(
                        if (newExpiredDate < 0) {
                            R.string.expired_date_permanent
                        } else {
                            R.string.note_expired_at_ps
                        },
                        Util.millisToDateString(newExpiredDate)
                    ),
                )
                // Change expired date
                Button(onClick = {
                    DatePickerDialog(
                        context,
                        { _, year, month, day ->
                            Calendar.getInstance().run {
                                set(year, month, day)
                                newExpiredDate = timeInMillis
                            }
                            viewModel.invokeChangeExpiredDateNote(note, newExpiredDate)
                        },
                        calNow.get(Calendar.YEAR),
                        calNow.get(Calendar.MONDAY),
                        calNow.get(Calendar.DAY_OF_MONTH)
                    ).apply {
                        datePicker.minDate = calNow.timeInMillis
                    }.show()
                }) {
                    Text(text = stringResource(R.string.change_expired_date))
                }
                // Make this note permanent
                Button(onClick = { viewModel.invokeChangeExpiredDateNote(note, -1) }) {
                    Text(text = stringResource(R.string.make_expired_date_permanent))
                }
            }

            // Buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Delete note
                if (deleteNoteTimerCount <= 0) {
                    Button(onClick = { deleteNoteTimerCount = 3 }) {
                        Text(text = stringResource(R.string.delete_note))
                    }
                } else {
                    Button(onClick = {
                        deleteNoteTimerCount = 0
                        viewModel.invokeDeleteNote(note)
                        dismissDialog()
                    }) {
                        Text(text = deleteNoteTimerCount.toString())
                    }
                    LaunchedEffect(key1 = deleteNoteTimerCount) {
                        delay(500L)
                        deleteNoteTimerCount -= 1
                    }
                }

                // Cancel
                Button(onClick = dismissDialog) { Text(text = stringResource(R.string.cancel)) }
            }
        }
    }
}
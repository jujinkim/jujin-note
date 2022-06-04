package com.jujinkim.note.ui.notelist

import android.app.Activity
import android.app.DatePickerDialog
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.model.Note
import com.jujinkim.note.ui.*
import com.jujinkim.note.ui.R
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

    var isShowOptionDialog by remember { mutableStateOf(false) }
    var isShowActionDialog by remember { mutableStateOf(false) }
    var selectedNoteForDialog by remember { mutableStateOf(Note.new("", "")) }

    Scaffold (
        topBar = { NoteListTopBar() },
        backgroundColor = com.jujinkim.note.ui.theme.LocalColors.current.background,
        contentColor = com.jujinkim.note.ui.theme.LocalColors.current.onBackground
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(viewModel.notes) { note ->
                    NoteItemCompose(
                        note = note,
                        onClick = { isShowActionDialog = true; selectedNoteForDialog = note },
                        onLongClick = { isShowOptionDialog = true; selectedNoteForDialog = note }
                    )
                }
            }
            NoteInput { viewModel.invokeAddNote(it) }
        }
    }

    NoteOptionDialog(
        isShowDialog = isShowOptionDialog,
        note = selectedNoteForDialog,
        onDismiss = { isShowOptionDialog = false }
    )

    NoteActionDialog(
        isShowDialog = isShowActionDialog,
        note = selectedNoteForDialog,
        onDismiss = { isShowActionDialog = false }
    )
}

@Composable
fun NoteListTopBar(viewModel: NoteListViewModel = hiltViewModel()) {
    val isWideScreen = isWideScreen()
    Row(
        modifier = Modifier.height(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isWideScreen) {
            IconButton(onClick = { viewModel.invokeBackToCategories() }) {
                Icon(AppIcons.ArrowBack, stringResource(id = R.string.back) )
            }
        }
        Text(text = viewModel.categoryName)
    }

}

@Composable
fun NoteInput(onNoteAddClick: (note: String) -> Unit) {
    val viewModel: NoteListViewModel = hiltViewModel()
    val noteInput = remember { mutableStateOf(viewModel.draftNote) }

    Row {
        TextField(
            modifier = Modifier
                .weight(1f)
                .onFocusChanged { if(!it.isFocused) viewModel.invokeUpdateDraftNote(noteInput.value) },
            value = noteInput.value,
            onValueChange = { noteInput.value = it }
        )
        IconButton(onClick = { onNoteAddClick(noteInput.value); noteInput.value = "" }) {
            Icon(AppIcons.PostAdd, stringResource(id = R.string.add_note))
        }
    }
}

@Composable
fun NoteOptionDialog(isShowDialog: Boolean, note: Note, onDismiss: () -> Unit) {
    val viewModel: NoteListViewModel = hiltViewModel()

    AppDialog(isShowDialog = isShowDialog, onDismiss = onDismiss) {
        var newExpiredDate by remember { mutableStateOf(note.expiredTime) }
        val calNow = Calendar.getInstance().apply {
            if (newExpiredDate >= 0) timeInMillis = newExpiredDate
        }
        val context = LocalContext.current

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
                IconButton(onClick = {
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
                        datePicker.minDate = Calendar.getInstance().timeInMillis
                    }.show()
                }) {
                    Icon(AppIcons.Event, stringResource(R.string.change_expired_date))
                }
                // Make this note permanent
                IconButton(onClick = {
                    newExpiredDate = -1
                    viewModel.invokeChangeExpiredDateNote(note, newExpiredDate)
                }) {
                    Icon(AppIcons.EventBusy, stringResource(R.string.make_expired_date_permanent))
                }
            }

            // Buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Delete note
                var deleteNoteTimerCount by remember { mutableStateOf(0) }
                if (deleteNoteTimerCount <= 0) {
                    IconButton(onClick = { deleteNoteTimerCount = 3 }) {
                        Icon(AppIcons.Delete, stringResource(R.string.delete_note))
                    }
                } else {
                    IconButton(onClick = {
                        deleteNoteTimerCount = 0
                        viewModel.invokeDeleteNote(note)
                        onDismiss()
                    }) {
                        Text(text = deleteNoteTimerCount.toString())
                    }
                    LaunchedEffect(key1 = deleteNoteTimerCount) {
                        delay(500L)
                        deleteNoteTimerCount -= 1
                    }
                }

                // Cancel
                IconButton(onClick = onDismiss) {
                    Icon(AppIcons.Check, stringResource(R.string.cancel))
                }
            }
        }
    }
}

@Composable
fun NoteActionDialog(isShowDialog: Boolean, note: Note, onDismiss: () -> Unit) {
    val viewModel: NoteListViewModel = hiltViewModel()

    AppDialog(isShowDialog = isShowDialog, onDismiss = onDismiss) {
        Row {
            // Copy
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.copyNoteToClipboard(note) }
            ) {
                Icon(AppIcons.ContentCopy, stringResource(R.string.copy_clipboard))
            }

            // Share
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { viewModel.shareNote(note) }
            ) {
                Icon(AppIcons.Share, stringResource(R.string.share))
            }

            // Cancel
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = onDismiss
            ) {
                Icon(AppIcons.Close, stringResource(R.string.cancel))
            }
        }
    }
}
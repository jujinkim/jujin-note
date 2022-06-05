package com.jujinkim.note.ui.notelist

import android.app.Service
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.NoteAction
import com.jujinkim.note.core.UiAction
import com.jujinkim.note.model.Note
import com.jujinkim.note.ui.R
import com.jujinkim.note.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val store: Store<AppState>,
    @ApplicationContext private val context: Context
) : ViewModel() {
    var categoryId by mutableStateOf(store.state.categoryId)
    var categoryName by mutableStateOf(store.state.categoryTitle)
    var notes by mutableStateOf(store.state.notes[categoryId]?.toList() ?: listOf())
    var draftNote by mutableStateOf(store.state.draftNotes[categoryId] ?: "")

    private val unsubscribe = store.subscribe {
        categoryId = store.state.categoryId
        categoryName = store.state.categoryTitle
        notes = store.state.notes[categoryId]?.toList() ?: listOf()
        draftNote = store.state.draftNotes[categoryId] ?: ""
    }

    fun invokeBackToCategories() {
        store.dispatch(UiAction.NavigateToCategories)
    }

    fun invokeAddNote(content: String) {
        if (content.isBlank()) {
            return
        }
        store.dispatch(NoteAction.AddNote(
            Note.new(
                categoryId,
                content,
                expiredTime = Util.calcExpiredTimeByDays(
                    System.currentTimeMillis(),
                    store.state.setting.defaultExpiredDay
                )
            )
        ))
    }

    fun invokeDeleteNote(note: Note) {
        store.dispatch(NoteAction.DeleteNote(note))
    }

    fun invokeChangeExpiredDateNote(note: Note, newExpiredDate: Long) {
        store.dispatch(NoteAction.UpdateNote(note.copy(expiredTime = newExpiredDate)))
    }

    fun copyNoteToClipboard(note: Note) {
        val cm = context.getSystemService(Service.CLIPBOARD_SERVICE) as ClipboardManager
        cm.setPrimaryClip(ClipData.newPlainText(note.content, note.content))
    }

    fun shareNote(note: Note) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, note.content)
        }

        val shortContent = note.content.take(20)

        context.startActivity(
            Intent.createChooser(
                intent,
                context.getString(R.string.share_from_note_ps, shortContent)
            ).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        )
    }

    fun invokeUpdateDraftNote(text: String) {
        draftNote = text
        store.dispatch(NoteAction.UpdateDraftNote(categoryId, text))
    }


    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
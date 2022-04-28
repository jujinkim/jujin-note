package com.jujinkim.note.ui.notelist

import android.app.Service
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.NoteAction
import com.jujinkim.note.core.UiAction
import com.jujinkim.note.model.Note
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

    private val unsubscribe = store.subscribe {
        categoryId = store.state.categoryId
        categoryName = store.state.categoryTitle
        notes = store.state.notes[categoryId]?.toList() ?: listOf()
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
        // Todo()
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
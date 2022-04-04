package com.jujinkim.note.ui.notelist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.NoteAction
import com.jujinkim.note.core.UiAction
import com.jujinkim.note.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val store: Store<AppState>
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
                content
            )
        ))
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
package com.jujinkim.note.ui.notelist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.UiAction
import com.jujinkim.note.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val store: Store<AppState>
) : ViewModel() {
    var categoryId by mutableStateOf("")
    val notes: List<Note>
    get() = store.state.notes[categoryId]?.toList() ?: listOf()

    private val unsubscribe = store.subscribe {
        categoryId = store.state.focusedCategoryId
    }

    fun invokeBackToCategories() {
        store.dispatch(UiAction.NavigateToCategories)
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
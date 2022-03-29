package com.jujinkim.note.ui.categorylist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppState
import com.jujinkim.note.model.NoteCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val store: Store<AppState>
) : ViewModel() {
    var categories by mutableStateOf<List<NoteCategory>>(listOf())

    private val unsubscribe = store.subscribe {
        categories = store.state.categories
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
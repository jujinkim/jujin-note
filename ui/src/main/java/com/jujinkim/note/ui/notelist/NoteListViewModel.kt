package com.jujinkim.note.ui.notelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppState
import com.jujinkim.note.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val store: Store<AppState>
) : ViewModel() {
    val categoryId = MutableLiveData("")

    private val unsubscribe = store.subscribe {
        categoryId.value = store.state.focusedCategoryId
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
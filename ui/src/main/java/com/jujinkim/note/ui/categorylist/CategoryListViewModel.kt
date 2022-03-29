package com.jujinkim.note.ui.categorylist

import androidx.lifecycle.MutableLiveData
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
    val categories = MutableLiveData(listOf<NoteCategory>())

    private val unsubscribe = store.subscribe {
        categories.value = store.state.categories
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
package com.jujinkim.note.ui.categorylist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.DatabaseThunks
import com.jujinkim.note.core.NoteAction
import com.jujinkim.note.core.NoteRepoLoadItemType
import com.jujinkim.note.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val store: Store<AppState>,
    private val dbThunk: DatabaseThunks
) : ViewModel() {
    var categories by mutableStateOf(listOf<Category>())

    private val unsubscribe = store.subscribe {
        categories = store.state.categories.toList()    // call toList() to create new list
    }

    fun invokeLoadCategories() {
        store.dispatch(dbThunk.getItems(NoteRepoLoadItemType.CATEGORIES))
    }

    fun invokeAddCategory(name: String) {
        if (name.isNotBlank()) {
            store.dispatch(NoteAction.AddCategory(Category.new(name)))
        }
    }

    fun invokeRemoveCategories(category: Category) {
        store.dispatch(NoteAction.RemoveCategory(category))
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
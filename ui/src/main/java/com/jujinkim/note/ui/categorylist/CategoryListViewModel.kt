package com.jujinkim.note.ui.categorylist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.*
import com.jujinkim.note.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val store: Store<AppState>,
) : ViewModel() {
    var categories by mutableStateOf(listOf<Category>())
    var isEditMode by mutableStateOf(false)

    private val unsubscribe = store.subscribe {
        categories = store.state.categories.toList()    // call toList() to create new list
        isEditMode = store.state.isCategoryEditMode
    }

    fun invokeAddCategory(name: String) {
        if (name.isNotBlank()) {
            store.dispatch(NoteAction.AddCategory(Category.new(name)))
        }
    }

    fun invokeEditCategory(category: Category, name: String) {
        if (name.isNotBlank()) {
            store.dispatch(NoteAction.UpdateCategory(category.copy(title = name)))
        }
    }

    fun invokeDeleteCategory(category: Category) {
        val relatedNotes = store.state.notes[category.id] ?: listOf()
        store.dispatch(NoteAction.DeleteCategory(category, relatedNotes))
    }

    fun invokeOpenNotes(category: Category) {
        store.dispatch(UiAction.NavigateToNotes(category))
    }

    fun invokeToggleCategoryEditMode() {
        store.dispatch(UiAction.ToggleCategoryEditMode)
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
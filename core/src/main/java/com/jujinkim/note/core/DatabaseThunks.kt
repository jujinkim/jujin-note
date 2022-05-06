package com.jujinkim.note.core

import com.jujinkim.note.data.repo.NoteRepo
import kotlinx.coroutines.*
import org.reduxkotlin.Thunk
import javax.inject.Inject

class DatabaseThunks @Inject constructor(private val repo: NoteRepo) {
    private val dbScope = CoroutineScope(Dispatchers.IO)

    fun getItems(
        itemType: NoteRepoLoadItemType,
        id: String = ""
    ): Thunk<AppState> = { dispatch, getState, extraArgs ->
        dbScope.launch {
            MainScope().launch { dispatch(NoteAction.GetFromDbStart(itemType)) }

            val data: Any = when (itemType) {
                NoteRepoLoadItemType.NOTE -> repo.getNote(id)
                NoteRepoLoadItemType.NOTES -> repo.getAllNotes()
                NoteRepoLoadItemType.CATEGORY -> repo.getCategory(id)
                NoteRepoLoadItemType.CATEGORIES -> repo.getCategories()
            }

            MainScope().launch { dispatch(NoteAction.GetFromDbSuccess(data, itemType)) }
        }
    }
}

enum class NoteRepoLoadItemType {
    NOTE, NOTES, CATEGORY, CATEGORIES
}
package com.jujinkim.note.core.initializer

import com.jujinkim.note.core.*
import org.reduxkotlin.Store
import javax.inject.Inject

class AppInitializerImpl @Inject constructor() : AppInitializer{
    @Inject lateinit var store: Store<AppState>
    @Inject lateinit var dbThunks: DatabaseThunks

    private var isInitialized = false
    override fun isInitialized() = isInitialized

    override fun initialize() {
        store.dispatch(SettingAction.LoadSetting)
        store.dispatch(dbThunks.getItems(NoteRepoLoadItemType.CATEGORIES))
        store.dispatch(dbThunks.getItems(NoteRepoLoadItemType.NOTES))
        store.dispatch(NoteAction.GetAllDraftNotes)

        isInitialized = true
    }
}
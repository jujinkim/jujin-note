package com.jujinkim.note.ui.setting

import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.UiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val store: Store<AppState>
) : ViewModel() {

    fun invokeBackToCategories() {
        store.dispatch(UiAction.NavigateToCategories)
    }
}
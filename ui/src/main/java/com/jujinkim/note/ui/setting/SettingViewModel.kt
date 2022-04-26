package com.jujinkim.note.ui.setting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.SettingAction
import com.jujinkim.note.core.UiAction
import com.jujinkim.note.model.Setting
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val store: Store<AppState>
) : ViewModel() {
    var currentSetting by mutableStateOf(store.state.setting)

    private val unsubscribe = store.subscribe {
        currentSetting = store.state.setting
    }

    fun invokeBackToCategories() {
        store.dispatch(UiAction.NavigateToCategories)
    }

    fun invokeSaveSetting(newSetting: Setting) {
        store.dispatch(SettingAction.SaveSetting(newSetting))
    }

    fun invokeLoadSetting() {
        store.dispatch(SettingAction.LoadSetting)
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
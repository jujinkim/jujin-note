package com.jujinkim.note.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppScreen
import com.jujinkim.note.core.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val store: Store<AppState>
) : ViewModel() {
    var currentScreen by mutableStateOf(AppScreen.CATEGORY_LIST)

    private val unsubscribe = store.subscribe {
        currentScreen = store.state.currentScreen
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
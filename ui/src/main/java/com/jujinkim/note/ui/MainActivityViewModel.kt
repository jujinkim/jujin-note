package com.jujinkim.note.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jujinkim.note.core.AppScreen
import com.jujinkim.note.core.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.Store
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val store: Store<AppState>
) : ViewModel() {
    val currentScreen = MutableLiveData(AppScreen.CATEGORY_LIST)

    private val unsubscribe = store.subscribe {
        currentScreen.value = store.state.currentScreen
    }

    override fun onCleared() {
        unsubscribe()
        super.onCleared()
    }
}
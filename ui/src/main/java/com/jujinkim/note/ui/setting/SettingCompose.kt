package com.jujinkim.note.ui.setting

import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.ui.BackHandler
import com.jujinkim.note.ui.R
import com.jujinkim.note.ui.isWideScreen

@Composable
fun SettingContent(viewModel: SettingViewModel = hiltViewModel()) {
    val activity = (LocalContext.current as? Activity)
    val isWideScreen = isWideScreen()
    BackHandler {
        if (!isWideScreen) {
            viewModel.invokeBackToCategories()
        } else {
            activity?.finish()
        }
    }

    Scaffold (
        topBar = { SettingTopBar() }
    ) {
        LazyColumn {

        }

    }

}

@Composable
fun SettingTopBar(viewModel: SettingViewModel = hiltViewModel()) {
    val isWideScreen = isWideScreen()
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (!isWideScreen) {
            Button(onClick = { viewModel.invokeBackToCategories() }) {
                Text(text = "<")
            }
        }
        Text(text = stringResource(id = R.string.settings))
    }

}
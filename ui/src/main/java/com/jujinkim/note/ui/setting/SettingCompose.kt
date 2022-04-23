package com.jujinkim.note.ui.setting

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
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
            item { SettingItemExpiredDay() }
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

@Composable
fun SettingItemExpiredDay(viewModel: SettingViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .clickable { }
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.settings_default_expired_date),
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(
                id = if (viewModel.currentSetting.defaultExpiredDay > 0)
                    R.string.settings_default_expired_date_after_pd_days
                else
                    R.string.settings_default_expired_date_permanent,
                viewModel.currentSetting.defaultExpiredDay
            ),
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}
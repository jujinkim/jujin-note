package com.jujinkim.note.ui.setting

import android.app.Activity
import android.widget.NumberPicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.ui.AppDialog
import com.jujinkim.note.ui.BackHandler
import com.jujinkim.note.ui.R
import com.jujinkim.note.ui.isWideScreen

@Composable
fun SettingContent(viewModel: SettingViewModel = hiltViewModel()) {
    val activity = (LocalContext.current as? Activity)
    val isWideScreen = isWideScreen()
    val isShowDialog = remember { mutableStateOf(false) }

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
            item { SettingItemExpiredDay(onClick = { isShowDialog.value = true }) }
        }
    }

    SettingExpiredDayDialog(
        isShowDialog = isShowDialog.value,
        onDismiss = { isShowDialog.value = false }
    )
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
fun SettingItemExpiredDay(
    viewModel: SettingViewModel = hiltViewModel(),
    onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
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
                id = if (viewModel.currentSetting.defaultExpiredDay >= 0)
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

@Composable
fun SettingExpiredDayDialog(isShowDialog: Boolean, onDismiss: () -> Unit) {
    val viewModel: SettingViewModel = hiltViewModel()
    val selectedNum = remember { mutableStateOf(viewModel.currentSetting.defaultExpiredDay) }
    val isNoteSavePermanent = viewModel.currentSetting.defaultExpiredDay < 0
    AppDialog(isShowDialog = isShowDialog, onDismiss = onDismiss) {
        Column {
            Text(text = stringResource(id = R.string.change_expired_date))
            // Permanent
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    viewModel.invokeSaveSetting(
                        viewModel.currentSetting.copy(defaultExpiredDay = -1)
                    )
                }
            ) {
                RadioButton(selected = isNoteSavePermanent, onClick = {
                    viewModel.invokeSaveSetting(
                        viewModel.currentSetting.copy(defaultExpiredDay = -1)
                    )
                })
                Text(text = stringResource(id = R.string.expired_date_permanent))
            }
            // Period
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    viewModel.invokeSaveSetting(
                        viewModel.currentSetting.copy(defaultExpiredDay = selectedNum.value)
                    )
                }
            ) {
                RadioButton(selected = !isNoteSavePermanent, onClick = {
                    viewModel.invokeSaveSetting(
                        viewModel.currentSetting.copy(defaultExpiredDay = selectedNum.value)
                    )
                })
                AndroidView(factory = { context ->
                    NumberPicker(context).apply {
                        value = selectedNum.value
                        minValue = 1; maxValue = 30
                        setOnValueChangedListener { _, _, newVal ->
                            selectedNum.value = newVal
                            viewModel.invokeSaveSetting(
                                viewModel.currentSetting.copy(defaultExpiredDay = newVal)
                            )
                        }
                    }
                })
            }
        }
    }
}
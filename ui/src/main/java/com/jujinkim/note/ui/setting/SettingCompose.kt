package com.jujinkim.note.ui.setting

import android.app.Activity
import android.widget.NumberPicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.ui.*
import com.jujinkim.note.ui.R

@Composable
fun SettingContent(viewModel: SettingViewModel = hiltViewModel()) {
    val activity = (LocalContext.current as? Activity)
    val isWideScreen = isWideScreen()
    var isShowDialog by remember { mutableStateOf(false) }

    BackHandler {
        if (!isWideScreen) {
            viewModel.invokeBackToCategories()
        } else {
            activity?.finish()
        }
    }

    Scaffold (
        topBar = { SettingTopBar() },
        backgroundColor = com.jujinkim.note.ui.theme.LocalColors.current.background,
        contentColor = com.jujinkim.note.ui.theme.LocalColors.current.onBackground
    ) {
        LazyColumn {
            item { SettingItemExpiredDay(onClick = { isShowDialog = true }) }
        }
    }

    SettingExpiredDayDialog(
        isShowDialog = isShowDialog,
        onDismiss = { isShowDialog = false }
    )
}

@Composable
fun SettingTopBar(viewModel: SettingViewModel = hiltViewModel()) {
    val isWideScreen = isWideScreen()
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (!isWideScreen) {
            IconButton(onClick = { viewModel.invokeBackToCategories() }) {
                Icon(AppIcons.ArrowBack, stringResource(id = R.string.back) )
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
            color = com.jujinkim.note.ui.theme.LocalColors.current.textGrayed
        )
    }
}

@Composable
fun SettingExpiredDayDialog(isShowDialog: Boolean, onDismiss: () -> Unit) {
    val viewModel: SettingViewModel = hiltViewModel()
    var selectedNum = viewModel.currentSetting.defaultExpiredDay
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
                        viewModel.currentSetting.copy(defaultExpiredDay = selectedNum)
                    )
                }
            ) {
                RadioButton(selected = !isNoteSavePermanent, onClick = {
                    viewModel.invokeSaveSetting(
                        viewModel.currentSetting.copy(defaultExpiredDay = selectedNum)
                    )
                })
                AndroidView(factory = { context ->
                    NumberPicker(context).apply {
                        //textColor = com.jujinkim.note.ui.theme.LocalColors.current.onDefault
                        minValue = 1; maxValue = 30
                        value = selectedNum
                        setOnValueChangedListener { _, _, newVal ->
                            selectedNum = newVal
                            viewModel.invokeSaveSetting(
                                viewModel.currentSetting.copy(defaultExpiredDay = newVal)
                            )
                        }
                    }
                })
            }
            IconButton(
                onClick = onDismiss,
                modifier = Modifier.wrapContentSize().align(Alignment.End)
            ) {
                Icon(AppIcons.Check, stringResource(id = R.string.close))
            }
        }
    }
}
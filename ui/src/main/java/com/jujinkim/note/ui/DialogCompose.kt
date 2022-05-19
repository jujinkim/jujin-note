package com.jujinkim.note.ui

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jujinkim.note.ui.theme.LocalColors
import com.jujinkim.note.ui.theme.Shapes

@Composable
fun AppDialog(isShowDialog: Boolean, onDismiss: () -> Unit, content: @Composable () -> Unit) {
    if (isShowDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier
                    .width(320.dp)
                    .wrapContentHeight(),
                shape = Shapes.medium,
                color = LocalColors.current.background,
                contentColor = LocalColors.current.onBackground,
                content = content
            )
        }
    }
}
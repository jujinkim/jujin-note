package com.jujinkim.note.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember


object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
//    val typography: AppTypography
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalTypography.current
//    val dimensions: AppDimensions
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalDimensions.current
}

@Composable
fun AppTheme(
    colors: AppColors = AppTheme.colors,
    typography: Typography = MaterialTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colors.copy()
    }.apply { updateColorsFrom(colors) }
    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalContentAlpha provides ContentAlpha.high,
        LocalIndication provides rippleIndication,
        //LocalTypography provides typography
    ) {
        content()
    }
}
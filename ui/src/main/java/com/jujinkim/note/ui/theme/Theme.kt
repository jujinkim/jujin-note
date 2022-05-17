package com.jujinkim.note.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

val LightColorPalette = AppColors(
    primary = Red,
    secondary = Green,
    background = White,
    border = GrayBright,
    error = RedBright,
    onPrimary = White,
    onSecondary = White,
    onBackground = Black,
    onDialog = Black,
    onError = Black,
    textGrayed = GrayBright,
    isLight = true
)

val DarkColorPalette = AppColors(
    primary = RedDark,
    secondary = GreenDark,
    background = Black,
    border = GrayDark,
    error = RedDark,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onDialog = White,
    onError = White,
    textGrayed = GrayDark,
    isLight = false
)

@Composable
fun JujinNoteTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    AppTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}
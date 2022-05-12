package com.jujinkim.note.ui.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
    primary: Color,
    secondary: Color,
    background: Color,
    border: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onDialog: Color,
    onError: Color,
    textGrayed: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var background by mutableStateOf(background)
        private set
    var border by mutableStateOf(border)
        private set
    var error by mutableStateOf(error)
        private set

    var onPrimary by mutableStateOf(onPrimary)
        private set
    var onSecondary by mutableStateOf(onSecondary)
        private set
    var onBackground by mutableStateOf(onBackground)
        private set
    var onDialog by mutableStateOf(onDialog)
        private set
    var onError by mutableStateOf(onError)
        private set
    var textGrayed by mutableStateOf(textGrayed)
        private set

    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        background: Color = this.background,
        border: Color = this.border,
        error: Color = this.error,
        onPrimary: Color = this.onPrimary,
        onSecondary: Color = this.onSecondary,
        onDefault: Color = this.onBackground,
        onDialog: Color = this.onDialog,
        onError: Color = this.onError,
        textGrayed: Color = this.textGrayed,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        primary,
        secondary,
        background,
        border,
        error,
        onPrimary,
        onSecondary,
        onDefault,
        onDialog,
        onError,
        textGrayed,
        isLight
    )

    fun updateColorsFrom(colors: AppColors) {
        primary = colors.primary
        secondary = colors.secondary
        background = colors.background
        border = colors.border
        error = colors.error
        onPrimary = colors.onPrimary
        onSecondary = colors.onSecondary
        onBackground = colors.onBackground
        onDialog = colors.onDialog
        onError = colors.onError
        textGrayed = colors.textGrayed
        isLight = colors.isLight
    }

}

internal val LocalColors = staticCompositionLocalOf { LightColorPalette }

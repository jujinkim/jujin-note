package com.jujinkim.note.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.jujinkim.note.ui.categorylist.NoteCategoryListContent

@Composable
fun MainActivityPhoneContent() {
    NoteCategoryListContent()
}

@Composable
fun MainActivityTabletContent() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        NoteCategoryListContent()
    }
}
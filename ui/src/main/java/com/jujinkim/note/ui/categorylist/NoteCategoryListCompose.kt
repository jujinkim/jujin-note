package com.jujinkim.note.ui.categorylist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jujinkim.note.ui.LocalState

@Composable
fun NoteCategoryListContent() {
    Scaffold(
        topBar = { NoteCategoryListTopBar() },
        content = { NoteCategoryList() }
    )
}

@Composable
fun NoteCategoryListTopBar() {
    Row {
        Text(
            text = "JujinNote",
            modifier = Modifier.fillMaxWidth()
        )
        IconButton(onClick = { /*TODO*/ }) {}
    }
}

@Composable
fun NoteCategoryList() {
    val categories = LocalState.current.categories
    LazyColumn {
        items(categories) {
            CategoryListItem(category = it)
        }
    }
}
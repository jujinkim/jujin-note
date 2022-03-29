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
import com.jujinkim.note.model.NoteCategory

@Composable
fun NoteCategoryFragmentContent(categories: List<NoteCategory>) {
    Scaffold(
        topBar = { NoteCategoryFragmentTopBar() },
        content = { NoteCategoryFragmentList(categories) }
    )
}

@Composable
fun NoteCategoryFragmentTopBar() {
    Row {
        Text(
            text = "JujinNote",
            modifier = Modifier.fillMaxWidth()
        )
        IconButton(onClick = { /*TODO*/ }) {}
    }
}

@Composable
fun NoteCategoryFragmentList(categories: List<NoteCategory>) {
    LazyColumn {
        items(categories) {
            CategoryListItem(category = it)
        }
    }
}
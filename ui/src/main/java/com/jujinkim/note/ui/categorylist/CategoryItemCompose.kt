package com.jujinkim.note.ui.categorylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jujinkim.note.model.Category

@Composable
fun CategoryListItem(category: Category) {
    Row(
        modifier = Modifier
            .padding(16.dp)
    ) {
        //Image()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(text = category.title)
            Text(text = category.id)
        }
    }
}
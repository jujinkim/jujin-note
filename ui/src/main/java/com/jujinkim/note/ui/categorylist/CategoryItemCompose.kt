package com.jujinkim.note.ui.categorylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jujinkim.note.model.Category

@Composable
fun CategoryListItem(
    category: Category,
    viewModel: CategoryListViewModel,
    onEditCategoryClick: (category: Category) -> Unit,
    onRemoveCategoryClick: (category: Category) -> Unit
) {
    val isEditMode = viewModel.isEditMode
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable { viewModel.invokeOpenNotes(category) }
    ) {
        // drag to reorder
        if (isEditMode && category.id.isNotEmpty()) {
            //Image(        )
        }

        //Image()
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = category.title)
            Text(text = category.id)
        }

        if (isEditMode && category.id.isNotEmpty()) {
            // edit name
            Button(onClick = { onEditCategoryClick(category) }) {
                Text("edit")
            }

            // remove category
            Button(onClick = { onRemoveCategoryClick(category) }) {
                Text("delete")
            }
        }
    }
}
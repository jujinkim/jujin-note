package com.jujinkim.note.ui.categorylist

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.model.Category

@Preview(showBackground = true)
@Composable
fun CategoryListItemPreview() {
    CategoryListItem(
        category = Category("testId", "Title", 0),
        onEditCategoryClick = {},
        onRemoveCategoryClick = {}
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CategoryListItem(
    category: Category,
    onEditCategoryClick: (category: Category) -> Unit,
    onRemoveCategoryClick: (category: Category) -> Unit
) {
    val viewModel: CategoryListViewModel = hiltViewModel()
    val isEditVisible = viewModel.isEditMode && category.id.isNotEmpty()
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable { viewModel.invokeOpenNotes(category) }
    ) {
        // drag to reorder
        AnimatedVisibility(visible = isEditVisible, enter = expandHorizontally(), exit = shrinkHorizontally()) {
            //Image(        )
        }

        //Image()
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = category.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = category.id, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }

        AnimatedVisibility(visible = isEditVisible, enter = scaleIn(), exit = scaleOut()) {
            // edit name
            Button(onClick = { onEditCategoryClick(category) }) {
                Text("edit")
            }
        }
        AnimatedVisibility(visible = isEditVisible, enter = scaleIn(), exit = scaleOut()) {
        // remove category
            Button(onClick = { onRemoveCategoryClick(category) }) {
                Text("delete")
            }
        }
    }
}
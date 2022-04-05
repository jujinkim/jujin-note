package com.jujinkim.note.ui.categorylist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.model.Category
import com.jujinkim.note.ui.R

@Preview(showBackground = true)
@Composable
fun CategoryListPreview() {
    CategoryListContent()
}

@Composable
fun CategoryListContent(
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { CategoryListTopBar() },
        content = { CategoryList() },
        floatingActionButton = { CategoryAddButton() }
    )
}

@Composable
fun CategoryListTopBar(viewModel: CategoryListViewModel = hiltViewModel()) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.weight(1f)
        )
        // edit list
        Button(onClick = { viewModel.invokeToggleCategoryEditMode() }) {
            Text("edit")
        }
        // go to setting
        Button(onClick = { /*TODO*/ }) {
            Text("setting")
        }
    }
}

@Composable
fun CategoryList(
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    val isShowEditDialog = remember { mutableStateOf(false) }
    val isShowRemoveDialog = remember { mutableStateOf(false) }
    val categoryForDialog = remember { mutableStateOf(Category.defaultCategory()) }

    LazyColumn {
        item {
            CategoryListItem(category = Category.defaultCategory(), viewModel = viewModel, {}, {})
        }
        items(viewModel.categories) { category ->
            CategoryListItem(
                category = category,
                viewModel,
                { isShowEditDialog.value = true; categoryForDialog.value = category },
                { isShowRemoveDialog.value = true; categoryForDialog.value = category }
            )
        }
    }

    CategoryEditDialog(
        isShowDialog = isShowEditDialog.value,
        category = categoryForDialog.value,
        onDismiss = { isShowEditDialog.value = false }
    )
    CategoryRemoveDialog(
        isShowDialog = isShowRemoveDialog.value,
        category = categoryForDialog.value,
        onDismiss = { isShowRemoveDialog.value = false }
    )
}

@Composable
fun CategoryAddButton() {
    val isShowDialog = remember { mutableStateOf(false) }
    if (isShowDialog.value) {
        CategoryAddDialog(isShowDialog = isShowDialog.value, onDismiss = { isShowDialog.value = false })
    }
    
    FloatingActionButton(onClick = { isShowDialog.value = true }) {
        Text(text = "+")
    }
}

@Composable
fun CategoryAddDialog(isShowDialog: Boolean, onDismiss: () -> Unit) {
    val viewModel: CategoryListViewModel = hiltViewModel()
    if (isShowDialog) {
        val input = remember { mutableStateOf("") }

        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier
                    .width(320.dp)
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.background
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.add_category))
                    TextField(value = input.value, onValueChange = { input.value = it}, placeholder = {})
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(onClick = onDismiss) { Text(text = stringResource(R.string.cancel)) }
                        Button(onClick = { viewModel.invokeAddCategory(input.value); onDismiss() }) {
                            Text(text = stringResource(R.string.okay))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryEditDialog(isShowDialog: Boolean, category: Category, onDismiss: () -> Unit) {
    val viewModel: CategoryListViewModel = hiltViewModel()
    if (isShowDialog) {
        val input = remember { mutableStateOf("") }

        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier
                    .width(320.dp)
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.background
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.edit_category_name))
                    TextField(value = input.value, onValueChange = { input.value = it}, placeholder = {})
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(onClick = onDismiss) { Text(text = stringResource(R.string.cancel)) }
                        Button(onClick = { viewModel.invokeEditCategory(category, input.value); onDismiss() }) {
                            Text(text = stringResource(R.string.okay))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryRemoveDialog(isShowDialog: Boolean, category: Category, onDismiss: () -> Unit) {
    val viewModel: CategoryListViewModel = hiltViewModel()
    if (isShowDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier
                    .width(320.dp)
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.background
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.remove_category_dialog_ps, category.title))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(onClick = onDismiss) { Text(text = stringResource(R.string.cancel)) }
                        Button(onClick = { viewModel.invokeDeleteCategory(category); onDismiss() }) {
                            Text(text = stringResource(R.string.okay))
                        }
                    }
                }
            }
        }
    }
}
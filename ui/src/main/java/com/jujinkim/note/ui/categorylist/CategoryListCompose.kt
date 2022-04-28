package com.jujinkim.note.ui.categorylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.model.Category
import com.jujinkim.note.ui.AppDialog
import com.jujinkim.note.ui.AppIcons
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
        IconToggleButton(
            checked = viewModel.isEditMode,
            onCheckedChange = { viewModel.invokeToggleCategoryEditMode() }
        ) {
            if (viewModel.isEditMode) {
                Icon(AppIcons.EditOff, stringResource(id = R.string.edit_category_done))
            } else {
                Icon(AppIcons.EditNote, stringResource(id = R.string.edit_category))
            }
        }
        // go to setting
        IconButton(onClick = { viewModel.invokeOpenSetting() }) {
            Icon(AppIcons.Settings, stringResource(id = R.string.settings))
        }
    }
}

@Composable
fun CategoryList(
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    var isShowEditDialog by remember { mutableStateOf(false) }
    var isShowRemoveDialog by remember { mutableStateOf(false) }
    var categoryForDialog by remember { mutableStateOf(Category.defaultCategory()) }

    LazyColumn {
        item {
            CategoryListItem(category = Category.defaultCategory(), {}, {})
        }
        items(viewModel.categories) { category ->
            CategoryListItem(
                category = category,
                { isShowEditDialog = true; categoryForDialog = category },
                { isShowRemoveDialog = true; categoryForDialog = category }
            )
        }
    }

    CategoryEditDialog(
        isShowDialog = isShowEditDialog,
        category = categoryForDialog,
        onDismiss = { isShowEditDialog = false }
    )
    CategoryRemoveDialog(
        isShowDialog = isShowRemoveDialog,
        category = categoryForDialog,
        onDismiss = { isShowRemoveDialog = false }
    )
}

@Composable
fun CategoryAddButton() {
    var isShowDialog by remember { mutableStateOf(false) }
    if (isShowDialog) {
        CategoryAddDialog(isShowDialog = isShowDialog, onDismiss = { isShowDialog = false })
    }
    
    FloatingActionButton(onClick = { isShowDialog = true }) {
        Icon(AppIcons.CreateNewFolder, stringResource(id = R.string.add_category))
    }
}

@Composable
fun CategoryAddDialog(isShowDialog: Boolean, onDismiss: () -> Unit) {
    val viewModel: CategoryListViewModel = hiltViewModel()
    var input by remember { mutableStateOf("") }
    AppDialog(isShowDialog = isShowDialog, onDismiss = onDismiss) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.add_category))
            TextField(value = input, onValueChange = { input = it}, placeholder = {})
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                IconButton(onClick = onDismiss) {
                    Icon(AppIcons.Cancel, stringResource(R.string.cancel))
                }
                IconButton(onClick = { viewModel.invokeAddCategory(input); onDismiss() }) {
                    Icon(AppIcons.Check, stringResource(R.string.okay))
                }
            }
        }
    }
}

@Composable
fun CategoryEditDialog(isShowDialog: Boolean, category: Category, onDismiss: () -> Unit) {
    val viewModel: CategoryListViewModel = hiltViewModel()
    var input by remember { mutableStateOf("") }
    AppDialog(isShowDialog = isShowDialog, onDismiss = onDismiss) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.edit_category_name))
            TextField(value = input, onValueChange = { input = it}, placeholder = {})
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                IconButton(onClick = onDismiss) {
                    Icon(AppIcons.Close, stringResource(R.string.cancel))
                }
                IconButton(onClick = { viewModel.invokeEditCategory(category, input); onDismiss() }) {
                    Icon(AppIcons.Check, stringResource(R.string.okay))
                }
            }
        }
    }
}

@Composable
fun CategoryRemoveDialog(isShowDialog: Boolean, category: Category, onDismiss: () -> Unit) {
    val viewModel: CategoryListViewModel = hiltViewModel()
    AppDialog(isShowDialog = isShowDialog, onDismiss = onDismiss) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.remove_category_dialog_ps, category.title))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                IconButton(onClick = onDismiss) {
                    Icon(AppIcons.Close, stringResource(R.string.cancel))
                }
                IconButton(onClick = { viewModel.invokeDeleteCategory(category); onDismiss() }) {
                    Icon(AppIcons.Check, stringResource(R.string.okay))
                }
            }
        }
    }
}
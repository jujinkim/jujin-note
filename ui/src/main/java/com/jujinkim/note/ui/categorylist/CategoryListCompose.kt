package com.jujinkim.note.ui.categorylist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujinkim.note.model.Category
import com.jujinkim.note.ui.R

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
fun CategoryListTopBar() {
    Row {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.fillMaxWidth()
        )
        IconButton(onClick = { /*TODO*/ }) {}
    }
}

@Composable
fun CategoryList(
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    LazyColumn {
        items(viewModel.categories) {
            CategoryListItem(category = it, viewModel)
        }
    }
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
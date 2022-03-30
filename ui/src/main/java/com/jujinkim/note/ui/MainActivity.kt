package com.jujinkim.note.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jujinkim.note.ui.ui.theme.JujinNoteTheme
import dagger.hilt.android.AndroidEntryPoint

val localMainViewModel = compositionLocalOf<MainViewModel> {
    error("MainActivityViewModel not provided")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JujinNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CompositionLocalProvider(localMainViewModel provides viewModel) {
                        MainContent()
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    if (screenWidth < 550.dp) {
        MainActivityPhoneContent()
    } else {
        MainActivityTabletContent()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JujinNoteTheme {
        MainActivityTabletContent()
    }
}
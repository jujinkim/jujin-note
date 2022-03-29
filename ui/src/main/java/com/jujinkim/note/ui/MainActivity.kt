package com.jujinkim.note.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jujinkim.note.core.AppState
import com.jujinkim.note.ui.ui.theme.JujinNoteTheme
import dagger.hilt.android.AndroidEntryPoint
import org.reduxkotlin.Store
import javax.inject.Inject

val LocalState = compositionLocalOf<AppState> { error("AppState not provided") }

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var store: Store<AppState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JujinNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CompositionLocalProvider(LocalState provides store.state) {
                        MainActivityPhoneContent()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JujinNoteTheme {
        MainActivityPhoneContent()
    }
}
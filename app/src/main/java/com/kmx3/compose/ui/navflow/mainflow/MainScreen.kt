package com.kmx3.compose.ui.navflow.mainflow

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainScreen {
    @Composable
    fun Render(events: Events) {
        Text("MainScreen")
    }

    interface Events
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen().Render(object : MainScreen.Events {})
}
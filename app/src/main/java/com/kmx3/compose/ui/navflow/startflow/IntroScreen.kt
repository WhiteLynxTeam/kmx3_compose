package com.kmx3.compose.ui.navflow.startflow

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class IntroScreen {
    @Composable
    fun Render(events: Events) {
        Text("IntroScreen")
    }

    interface Events
}

@Preview
@Composable
fun PreviewIntroScreen() {
    IntroScreen().Render(object : IntroScreen.Events {})
}
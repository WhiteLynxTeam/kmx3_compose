package com.kmx3.compose.ui.navflow.startflow

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmx3.compose.R
import kotlinx.coroutines.delay

@Composable
fun GreetingScreen(events: GreetingScreenEvents) {
    val name = "Мария"

    LaunchedEffect(Unit) {
        delay(1500)
        events.onNext()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            // Логотип (png/svg ресурс)
            Image(
                painter = painterResource(R.drawable.ic_logo_miel_bordo), // замените на ваш ресурс
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Добро пожаловать,",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "$name!",
                    style = MaterialTheme.typography.headlineSmall
                )
            }


        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(R.drawable.img_greeting),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
        }
    }

}

interface GreetingScreenEvents {
    fun onNext()
}

@Preview(showBackground = true)
@Composable
fun PreviewGreetingScreen() {
    GreetingScreen(object : GreetingScreenEvents {
        override fun onNext() {}
    })
}
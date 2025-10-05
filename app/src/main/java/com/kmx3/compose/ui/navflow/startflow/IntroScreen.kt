package com.kmx3.compose.ui.navflow.startflow

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

//class IntroScreen {
//    @Composable
//    fun Render(events: Events) {
//        VideoScreen()
//    }
//
//    interface Events
//}

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun VideoScreen(events: IntroScreenEvents) {
    val context = LocalContext.current

    // Создаем плеер
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            // Видео из ресурсов raw
            val videoUri = Uri.parse("android.resource://${context.packageName}/raw/splash")
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
            playWhenReady = true // автоматическое воспроизведение
        }
    }

    // Следим за окончанием видео
    var videoEnded by remember { mutableStateOf(false) }

    // Обработчик событий плеера
    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    videoEnded = true
                }
            }
        }

        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release() // освобождаем ресурсы
        }
    }

    // Автоматический переход при окончании видео
    LaunchedEffect(videoEnded) {
        if (videoEnded) {
            events.onNext()
        }
    }

    // Отображаем видео
    if (!videoEnded) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
        ) {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

interface IntroScreenEvents {
    fun onNext()
}

@Preview
@Composable
fun PreviewIntroScreen() {
//    IntroScreen().Render(object : IntroScreen.Events {})
    VideoScreen(object : IntroScreenEvents {
        override fun onNext() {}
    })
}
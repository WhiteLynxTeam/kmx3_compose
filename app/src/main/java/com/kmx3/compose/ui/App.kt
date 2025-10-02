package com.kmx3.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import javax.inject.Inject

class App @Inject constructor() {
    @Composable
    fun Render() {
        val navController = rememberNavController()

    }
}
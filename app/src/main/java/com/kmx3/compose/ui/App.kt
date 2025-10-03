package com.kmx3.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kmx3.compose.ui.mainflow.MainFlowNavigation
import javax.inject.Inject

class App @Inject constructor() {
    @Composable
    fun Render() {
        val navController = rememberNavController()

        val mainFlowNavigation =
            MainFlowNavigation(navController) {
                // nothing here - not supposed to end
            }

        val flows: NavGraphBuilder.() -> Unit = {
            mainFlowNavigation.addFlow(this)
        }

        NavHost(
            navController = navController,
            startDestination = mainFlowNavigation.startRoute,
            builder = flows
        )

    }
}
package com.kmx3.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kmx3.compose.ui.navflow.mainflow.MainFlowNavigation
import com.kmx3.compose.ui.navflow.startflow.StartFlowNavigation
import javax.inject.Inject

class App @Inject constructor() {
    @Composable
    fun Render() {
        val navController = rememberNavController()

        val mainFlowNavigation =
            MainFlowNavigation(navController) {
                // nothing here - not supposed to end
            }

        val startFlowNavigation =
            StartFlowNavigation(navController) {
                navController.navigate(mainFlowNavigation.startRoute) {
                    popUpTo(StartFlowNavigation.Routes.AuthScreen.route) {
                        inclusive = true
                    }
                }
            }

        val flows: NavGraphBuilder.() -> Unit = {
            startFlowNavigation.addFlow(this)
            mainFlowNavigation.addFlow(this)
        }

        NavHost(
            navController = navController,
            startDestination = startFlowNavigation.startRoute,
            builder = flows
        )

    }
}
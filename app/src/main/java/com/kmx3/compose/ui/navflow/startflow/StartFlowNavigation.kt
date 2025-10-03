package com.kmx3.compose.ui.navflow.startflow

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kmx3.compose.ui.navigation.SubFlowNavigation

class StartFlowNavigation(
    val navController: NavHostController,
    onFinished: (routeName: String) -> Unit
) : SubFlowNavigation(onFinished) {
    override val startRoute: String
        get() = Routes.AuthScreen.route

    override fun addFlow(builder: NavGraphBuilder) {
        with(builder) {
            composable(Routes.IntroScreen.route) {
                IntroScreen().Render(events = object : IntroScreen.Events {})
            }

            composable(Routes.AuthScreen.route) {
                AuthScreen().Render(events = object : AuthScreen.Events {})
            }
        }
    }

    sealed class Routes(val route: String) {
        data object IntroScreen : Routes("StartFlowNavigator.IntroScreen")
        data object AuthScreen : Routes("StartFlowNavigator.AuthScreen")
    }
}
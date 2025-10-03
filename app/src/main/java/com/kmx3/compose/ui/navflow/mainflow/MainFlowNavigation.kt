package com.kmx3.compose.ui.navflow.mainflow

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kmx3.compose.ui.navigation.SubFlowNavigation

class MainFlowNavigation(
    val navController: NavHostController,
    onFinished: (routeName: String) -> Unit
) : SubFlowNavigation(onFinished) {
    override val startRoute: String
        get() = Routes.MainScreen.route

    override fun addFlow(builder: NavGraphBuilder) {
        with(builder) {
            composable(Routes.MainScreen.route) {
                MainScreen().Render(events = object : MainScreen.Events {})
            }
        }
    }

    sealed class Routes(val route: String) {
        data object MainScreen : Routes("MainFlowNavigator.MainScreen")
    }
}
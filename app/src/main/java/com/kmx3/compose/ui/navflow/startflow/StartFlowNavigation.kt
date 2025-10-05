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
        get() = Routes.IntroScreen.route

    override fun addFlow(builder: NavGraphBuilder) {
        with(builder) {
            composable(Routes.IntroScreen.route) {
                VideoScreen(events = object : IntroScreenEvents {
                    override fun onNext() {
                        navController.navigate(Routes.AuthScreen.route)
                    }
                })
            }

//            composable(Routes.IntroScreen.route) {
//                VideoScreen().Render(events = object : VideoScreen.Events {
//                    override fun onNext() {
//                        navController.navigate(Routes.AuthScreen.route)
//                    }
//                })
//            }

            composable(Routes.AuthScreen.route) {
                AuthScreen().Render(events = object : AuthScreen.Events {
                    override fun onNext() {
                        navController.navigate(Routes.GreetingScreen.route) {
                            popUpTo(Routes.AuthScreen.route) { inclusive = true } // убираем экран логина
                            launchSingleTop = true
                        }
                    }
                })
            }

            composable(Routes.GreetingScreen.route) {
                GreetingScreen(events = object : GreetingScreenEvents {
                    override fun onNext() {
                        //navController.navigate(Routes.AuthScreen.route)
                    }
                })
            }
        }
    }

    sealed class Routes(val route: String) {
        data object IntroScreen : Routes("StartFlowNavigator.IntroScreen")
        data object AuthScreen : Routes("StartFlowNavigator.AuthScreen")
        data object GreetingScreen : Routes("StartFlowNavigator.GreetingScreen")
    }
}
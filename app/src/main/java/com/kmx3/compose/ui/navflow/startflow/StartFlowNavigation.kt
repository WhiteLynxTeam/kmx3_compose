package com.kmx3.compose.ui.navflow.startflow

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kmx3.compose.ui.navigation.SubFlowNavigation
import kotlinx.coroutines.flow.collectLatest

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
                        navController.navigate(Routes.AuthScreen.route) {
                            popUpTo(Routes.IntroScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                })
            }

            composable(Routes.AuthScreen.route) {

//                AuthScreen().Render(events = object : AuthScreen.Events {
//                    override fun onNext() {
//                        navController.navigate(Routes.GreetingScreen.route) {
//                            popUpTo(Routes.AuthScreen.route) { inclusive = true } // убираем экран логина
//                            launchSingleTop = true
//                        }
//                    }
//                })
//            }
//


                val viewModel = hiltViewModel<AuthScreenViewModel>()

                LaunchedEffect(key1 = true) {
                    viewModel.events.collectLatest { event ->
                        when (event) {
                            is AuthScreenViewModel.Events.Auth -> {
                                navController.navigate(Routes.GreetingScreen.route) {
                                    popUpTo(Routes.AuthScreen.route) {
                                        inclusive = true
                                    } // убираем экран логина
                                    launchSingleTop = true
                                }
                            }
                            is AuthScreenViewModel.Events.Exit -> {
                                navController.popBackStack()
                            }
                        }
                    }
                }

                val state by viewModel.state.collectAsState()
                AuthScreen(state, viewModel)
            }

            composable(Routes.GreetingScreen.route) {
                GreetingScreen(events = object : GreetingScreenEvents {
                    override fun onNext() {
                        finishFlow()
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
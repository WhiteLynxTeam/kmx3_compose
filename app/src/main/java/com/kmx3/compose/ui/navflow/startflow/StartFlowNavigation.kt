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

//            composable(Routes.IntroScreen.route) {
//                VideoScreen().Render(events = object : VideoScreen.Events {
//                    override fun onNext() {
//                        navController.navigate(Routes.AuthScreen.route)
//                    }
//                })
//            }

            composable(Routes.AuthScreen.route) {
                val viewModel = hiltViewModel<AuthScreenViewModel>()

                LaunchedEffect(key1 = true) {
                    viewModel.events.collectLatest { event ->
                        when (event) {
                            is AuthScreenViewModel.Events.Auth -> {
                                finishFlow()
                            }
                            is AuthScreenViewModel.Events.Exit -> {
                                navController.popBackStack()
                            }
                        }
                    }
                }

                val state by viewModel.state.collectAsState()
                AuthScreen(state, viewModel)

//                val viewModel = hiltViewModel(backStackEntry, null)
//
//
//                AuthScreen(state = , events = object : AuthScreen.Events {})
            }
        }
    }

    sealed class Routes(val route: String) {
        data object IntroScreen : Routes("StartFlowNavigator.IntroScreen")
        data object AuthScreen : Routes("StartFlowNavigator.AuthScreen")
    }
}
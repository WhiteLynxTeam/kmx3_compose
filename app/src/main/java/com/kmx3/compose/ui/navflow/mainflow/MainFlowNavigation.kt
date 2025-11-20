package com.kmx3.compose.ui.navflow.mainflow

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kmx3.compose.R
import com.kmx3.compose.ui.navflow.mainflow.favorites.FavoritesScreen
import com.kmx3.compose.ui.navflow.mainflow.favorites.FavoritesScreenViewModel
import com.kmx3.compose.ui.navflow.mainflow.invitations.InvitationsScreen
import com.kmx3.compose.ui.navflow.mainflow.invitations.InvitationsScreenViewModel
import com.kmx3.compose.ui.navflow.mainflow.quotas.QuotasScreen
import com.kmx3.compose.ui.navflow.mainflow.quotas.QuotasScreenViewModel
import com.kmx3.compose.ui.navflow.mainflow.showcase.ShowCaseScreenViewModel
import com.kmx3.compose.ui.navflow.mainflow.showcase.ShowcaseScreen
import com.kmx3.compose.ui.navigation.SubFlowNavigation
import kotlinx.coroutines.flow.collectLatest

class MainFlowNavigation(
    val navController: NavHostController,
    onFinished: (routeName: String) -> Unit
) : SubFlowNavigation(onFinished) {
//    var currentRoute = Routes.ShowcaseScreen

    override val startRoute: String
        get() = Routes.ShowcaseScreen.route

    override fun addFlow(builder: NavGraphBuilder) {
        with(builder) {
            composable(Routes.ShowcaseScreen.route) {
                val viewModel = hiltViewModel<ShowCaseScreenViewModel>()

                LaunchedEffect(key1 = true) {
                    viewModel.events.collectLatest { event ->
                        when (event) {
                            is ShowCaseScreenViewModel.Events.SelectBottomMenu -> {
                                navController.navigate(event.item.route)
                            }

                        }
                    }
                }

                val state by viewModel.state.collectAsState()
                val userProfile by viewModel.userProfile.collectAsState(initial = null)
                ShowcaseScreen(state, viewModel, userProfile)
            }
            composable(Routes.FavoritesScreen.route) {
                val viewModel = hiltViewModel<FavoritesScreenViewModel>()

                LaunchedEffect(key1 = true) {
                    viewModel.events.collectLatest { event ->
                        when (event) {
                            is FavoritesScreenViewModel.Events.SelectBottomMenu -> {
                                navController.navigate(event.item.route)
                            }

                        }
                    }
                }

                val state by viewModel.state.collectAsState()
                val userProfile by viewModel.userProfile.collectAsState(initial = null)
                FavoritesScreen(state, viewModel, userProfile)

/*                FavoritesScreen(
                    events = object : FavoritesScreenEvents {
                        override fun onRequestQuota() {

                        }
                    },
                    state = FavoritesScreenState(value = "")
                )*/
            }
            composable(Routes.InvitationsScreen.route) {
                val viewModel = hiltViewModel<InvitationsScreenViewModel>()

                LaunchedEffect(key1 = true) {
                    viewModel.events.collectLatest { event ->
                        when (event) {
                            is InvitationsScreenViewModel.Events.SelectBottomMenu -> {
                                navController.navigate(event.item.route)
                            }

                        }
                    }
                }

                val state by viewModel.state.collectAsState()
                val userProfile by viewModel.userProfile.collectAsState(initial = null)
                InvitationsScreen(state, viewModel, userProfile)
/*                InvitationsScreen(
                    events = object : InvitationsScreenEvents {
                        override fun onRequestQuota() {
                            // Логика обработки
                        }
                    },
                    state = InvitationsScreenState(value = "")
                )*/
            }
            composable(Routes.QuotasScreen.route) {
                val viewModel = hiltViewModel<QuotasScreenViewModel>()

                LaunchedEffect(key1 = true) {
                    viewModel.events.collectLatest { event ->
                        when (event) {
                            is QuotasScreenViewModel.Events.SelectBottomMenu -> {
                                navController.navigate(event.item.route)
                            }

                        }
                    }
                }

                val state by viewModel.state.collectAsState()
                val userProfile by viewModel.userProfile.collectAsState(initial = null)
                QuotasScreen(state, viewModel, userProfile)
/*                QuotasScreen(
                    events = object : QuotasScreenEvents {
                        override fun onRequestQuota() {
                            // Логика обработки
                        }
                    },
                    state = QuotasScreenState(value = "")
                )*/
            }
        }
    }
/*
    @Composable
    fun MainFlowScaffold() {
        val innerNavController = rememberNavController()
        val currentEntry by innerNavController.currentBackStackEntryAsState()
        val currentRoute = currentEntry?.destination?.route
        val selectedRoute = Routes.allRoutes.find { it.route == currentRoute } ?: Routes.ShowcaseScreen

        Scaffold(
            topBar = { UserProfileTopBar() },
            bottomBar = {
                BottomMenu(
                    items = Routes.allRoutes,
                    selected = selectedRoute,
                    onSelect = { route ->
                        innerNavController.navigate(route.route) {
                            popUpTo(innerNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = innerNavController,
                startDestination = Routes.ShowcaseScreen.route,
                modifier = Modifier.padding(innerPadding)
            ) {

            }
        }
    }*/

    sealed class Routes(
        val route: String,
        val iconActive: Int,
        val iconInactive: Int,
        val label: String,
    ) {
        data object ShowcaseScreen : Routes(
            route = "MainFlowNavigator.ShowcaseScreen",
            iconActive = R.drawable.ic_showcase_active,
            iconInactive = R.drawable.ic_showcase_inactive,
            label = "Витрина",
            )
        data object FavoritesScreen : Routes(
            route = "MainFlowNavigator.FavoritesScreen",
            iconActive = R.drawable.ic_favorites_active,
            iconInactive = R.drawable.ic_favorites_inactive,
            label = "Избранное",
        )
        data object InvitationsScreen : Routes(
            route = "MainFlowNavigator.InvitationsScreen",
            iconActive = R.drawable.ic_invitations_active,
            iconInactive = R.drawable.ic_invitations_inactive,
            label = "Приглашения",
            )
        data object QuotasScreen : Routes(
            route = "MainFlowNavigator.QuotasScreen",
            iconActive = R.drawable.ic_quotas_active,
            iconInactive = R.drawable.ic_quotas_inactive,
            label = "Квоты",
            )

        companion object {
            //Использовать val с ленивой инициализацией (by lazy),
            // чтобы доступ к объектам в списке происходил после полной инициализации всех объектов.

            //без lazy первый элемент был null

            //Для надежного кода избегай прямого доступа к объектам sealed class
            // в статической инициализации companion без ленивой обёртки

            val allRoutes: List<Routes> by lazy {
                listOf(
                    ShowcaseScreen,
                    FavoritesScreen,
                    InvitationsScreen,
                    QuotasScreen,
                )
            }
        }
    }
}
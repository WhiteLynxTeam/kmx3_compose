package com.kmx3.compose.ui.navflow.mainflow

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kmx3.compose.R
import com.kmx3.compose.ui.navflow.mainflow.favorites.FavoritesScreen
import com.kmx3.compose.ui.navflow.mainflow.favorites.FavoritesScreenEvents
import com.kmx3.compose.ui.navflow.mainflow.favorites.FavoritesScreenState
import com.kmx3.compose.ui.navflow.mainflow.invitations.InvitationsScreen
import com.kmx3.compose.ui.navflow.mainflow.invitations.InvitationsScreenEvents
import com.kmx3.compose.ui.navflow.mainflow.invitations.InvitationsScreenState
import com.kmx3.compose.ui.navflow.mainflow.main_navigation.UserProfileTopBar
import com.kmx3.compose.ui.navflow.mainflow.menu.BottomMenu
import com.kmx3.compose.ui.navflow.mainflow.quotas.QuotasScreen
import com.kmx3.compose.ui.navflow.mainflow.quotas.QuotasScreenEvents
import com.kmx3.compose.ui.navflow.mainflow.quotas.QuotasScreenState
import com.kmx3.compose.ui.navflow.mainflow.showcase.ShowcaseScreen
import com.kmx3.compose.ui.navflow.mainflow.showcase.ShowcaseScreenEvents
import com.kmx3.compose.ui.navflow.mainflow.showcase.ShowcaseScreenState
import com.kmx3.compose.ui.navigation.SubFlowNavigation

class MainFlowNavigation(
    val navController: NavHostController,
    onFinished: (routeName: String) -> Unit
) : SubFlowNavigation(onFinished) {
    override val startRoute: String
        get() = "main_flow_scaffold"

    override fun addFlow(builder: NavGraphBuilder) {
        with(builder) {
            composable("main_flow_scaffold") {
                MainFlowScaffold()
            }
        }
    }

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
                composable(Routes.ShowcaseScreen.route) {
                    ShowcaseScreen(
                        events = object : ShowcaseScreenEvents {
                            override fun onRequestQuota() {

                            }
                        },
                        state = ShowcaseScreenState(value = "")
                    )
                }
                composable(Routes.FavoritesScreen.route) {
                    FavoritesScreen(
                        events = object : FavoritesScreenEvents {
                            override fun onRequestQuota() {

                            }
                        },
                        state = FavoritesScreenState(value = "")
                    )
                }
                composable(Routes.InvitationsScreen.route) {
                    InvitationsScreen(
                        events = object : InvitationsScreenEvents {
                            override fun onRequestQuota() {
                                // Логика обработки
                            }
                        },
                        state = InvitationsScreenState(value = "")
                    )
                }
                composable(Routes.QuotasScreen.route) {
                    QuotasScreen(
                        events = object : QuotasScreenEvents {
                            override fun onRequestQuota() {
                                // Логика обработки
                            }
                        },
                        state = QuotasScreenState(value = "")
                    )
                }
            }
        }
    }

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
            val allRoutes = listOf(
                ShowcaseScreen,
                FavoritesScreen,
                InvitationsScreen,
                QuotasScreen
            )
        }
    }
}
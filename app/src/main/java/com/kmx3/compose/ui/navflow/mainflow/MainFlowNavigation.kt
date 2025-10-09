package com.kmx3.compose.ui.navflow.mainflow

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kmx3.compose.R
import com.kmx3.compose.ui.navflow.mainflow.favorites.FavoritesScreen
import com.kmx3.compose.ui.navflow.mainflow.favorites.FavoritesScreenEvents
import com.kmx3.compose.ui.navflow.mainflow.favorites.FavoritesScreenState
import com.kmx3.compose.ui.navflow.mainflow.showcase.ShowcaseScreen
import com.kmx3.compose.ui.navflow.mainflow.showcase.ShowcaseScreenEvents
import com.kmx3.compose.ui.navflow.mainflow.showcase.ShowcaseScreenState
import com.kmx3.compose.ui.navigation.SubFlowNavigation

class MainFlowNavigation(
    val navController: NavHostController,
    onFinished: (routeName: String) -> Unit
) : SubFlowNavigation(onFinished) {
    override val startRoute: String
        get() = Routes.ShowcaseScreen.route

    override fun addFlow(builder: NavGraphBuilder) {
        with(builder) {
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
    }
}
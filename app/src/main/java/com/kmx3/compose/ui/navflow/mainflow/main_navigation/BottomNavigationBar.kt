package com.kmx3.compose.ui.navflow.mainflow.main_navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kmx3.compose.R
import com.kmx3.compose.ui.theme.Bordo

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Showcase,
        BottomNavItem.Favorites,
        BottomNavItem.Invitations,
        BottomNavItem.Quotas
    )
    NavigationBar(
        containerColor = Bordo
    ) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination
        items.forEach { item ->
            val selected = currentDestination?.route == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            if (selected)
                                item.iconActive
                            else
                                item.iconInactive
                        ),
                        contentDescription = item.label,
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        item.label,
                        color = Color.White,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White
                )
            )
        }
    }
}

sealed class BottomNavItem(
    val route: String,
    val iconActive: Int,
    val iconInactive: Int,
    val label: String
) {
    object Showcase : BottomNavItem("home", R.drawable.ic_showcase_active, R.drawable.ic_showcase_inactive, "Витрина")
    object Favorites : BottomNavItem("favorites", R.drawable.ic_favorites_active, R.drawable.ic_favorites_inactive, "Избранное")
    object Invitations : BottomNavItem("invitations", R.drawable.ic_invitations_active, R.drawable.ic_invitations_inactive, "Приглашения")
    object Quotas : BottomNavItem("quotas", R.drawable.ic_quotas_active, R.drawable.ic_quotas_inactive, "Квоты")
}
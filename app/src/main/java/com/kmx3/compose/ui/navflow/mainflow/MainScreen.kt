package com.kmx3.compose.ui.navflow.mainflow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kmx3.compose.ui.navflow.mainflow.favorites.FavoritesScreen
import com.kmx3.compose.ui.navflow.mainflow.invitations.InvitationsScreen
import com.kmx3.compose.ui.navflow.mainflow.main_navigation.BottomNavItem
import com.kmx3.compose.ui.navflow.mainflow.main_navigation.BottomNavigationBar
import com.kmx3.compose.ui.navflow.mainflow.main_navigation.UserProfileTopBar
import com.kmx3.compose.ui.navflow.mainflow.quotas.QuotasScreen
import com.kmx3.compose.ui.navflow.mainflow.showcase.ShowcaseScreen

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen(
//    events: MainScreenEvents
//) {
//
//    val navController = rememberNavController()
//
//    Scaffold(
//        topBar = { UserProfileTopBar() },
//        bottomBar = {
//            BottomNavigationBar(navController = navController)
//        }
//    ) { innerPadding ->
//        Box(modifier = Modifier.padding(innerPadding)) {
//            NavHost(
//                navController = navController,
//                startDestination = BottomNavItem.Showcase.route
//            ) {
///*                composable(BottomNavItem.Showcase.route) { ShowcaseScreen(
//                    onRequestQuotaClick = { *//* обработка *//* },
//                    onFilterClick = { *//* обработка *//* }
//                ) }
//                composable(BottomNavItem.Favorites.route) { FavoritesScreen() }
//                composable(BottomNavItem.Invitations.route) { InvitationsScreen() }
//                composable(BottomNavItem.Quotas.route) { QuotasScreen() }*/
//            }
//        }
//    }
//}

//interface MainScreenEvents {
//    fun onNext()
//}

//@Preview
//@Composable
//fun PreviewMainScreen() {
//    MainScreen(object : MainScreenEvents {
//        override fun onNext() {}
//    })
//}
package com.kmx3.compose.ui.navflow.mainflow.menu

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.kmx3.compose.ui.navflow.mainflow.MainFlowNavigation
import com.kmx3.compose.ui.theme.Bordo

@Composable
fun BottomMenu(
//    items: List<MainFlowNavigation.Routes>,
    selected: MainFlowNavigation.Routes,
    onSelect: (item: MainFlowNavigation.Routes) -> Unit,
) {
    val items = MainFlowNavigation.Routes.allRoutes

    NavigationBar(
        containerColor = Bordo
    ) {
        items.forEach { item ->

            val isSelected = item == selected
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    println("Bottom menu item - $item")
                    onSelect(item)
                          },
                icon = {
                    Icon(
                        painter = painterResource(
                            if (isSelected)
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
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
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

@Preview(showBackground = true)
@Composable
fun BottomMenuPreview() {

    BottomMenu(
        selected = MainFlowNavigation.Routes.ShowcaseScreen,
        onSelect = {}
        )
}

@Preview(showBackground = true)
@Composable
fun BottomMenuPreview2() {

    BottomMenu(
        selected = MainFlowNavigation.Routes.InvitationsScreen,
        onSelect = {}
        )
}
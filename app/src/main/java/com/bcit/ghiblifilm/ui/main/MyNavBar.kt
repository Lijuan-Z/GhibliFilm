package com.bcit.ghiblifilm.ui.main

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

enum class Screen(val route:String){
    Home("home"),
    Detail("details"),
    WishList("wishlist")
}

data class NavItem(val icon:ImageVector,val route: String)
@Composable
fun MyNavBar(navController: NavController){
    val navItems = listOf(
        NavItem(Icons.Rounded.Home,Screen.Home.route),
        NavItem(Icons.Rounded.FavoriteBorder,Screen.WishList.route)
    )

    NavigationBar(
        modifier = Modifier.height(60.dp),
        containerColor = Color(0xFFE0E7EB)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        //navBackStackEntry is a state object
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach{
            NavigationBarItem(
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route)
                },
                icon = {
                    Icon(it.icon, contentDescription = null)
                })
        }

    }
}
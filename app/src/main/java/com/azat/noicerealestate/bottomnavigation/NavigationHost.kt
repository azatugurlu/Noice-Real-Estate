package com.azat.noicerealestate.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.azat.noicerealestate.screens.Favorites
import com.azat.noicerealestate.screens.Profile
import com.azat.noicerealestate.screens.home.Home
import com.azat.noicerealestate.screens.propertydetail.PropertyDetail

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Home.route,
    ) {
        composable(NavigationRoutes.Home.route) {
            Home(navController)
        }

        composable(NavigationRoutes.Profile.route) {
            Profile()
        }

        composable(NavigationRoutes.Favorites.route) {
            Favorites()
        }

        composable(
            NavigationRoutes.PropertyDetail.route,
            arguments = listOf(navArgument("propertyId") {
                type = NavType.StringType
            }
            )
        ) {
            PropertyDetail(navController, it.arguments?.getString("propertyId"))
        }
    }
}
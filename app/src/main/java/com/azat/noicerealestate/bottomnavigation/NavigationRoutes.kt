package com.azat.noicerealestate.bottomnavigation

sealed class NavigationRoutes(val route: String) {
    object Home : NavigationRoutes("home")
    object Profile : NavigationRoutes("profile")
    object Favorites : NavigationRoutes("favorites")
    object PropertyDetail : NavigationRoutes("propertyDetail/{propertyId}")
}
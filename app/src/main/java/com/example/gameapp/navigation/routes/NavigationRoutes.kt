package com.example.gameapp.navigation.routes

sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Unauthenticated(route = "unauthenticated")
        data object SplashScreen : Unauthenticated(route = "splash_screen")
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Authenticated(route = "authenticated")
        data object DashboardScreen : Authenticated(route = "dashboard_screen")
        data object DetailGameScreen : Authenticated("gameDetail/{gameId}")
    }
}
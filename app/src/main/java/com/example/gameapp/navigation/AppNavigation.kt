package com.example.gameapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.gameapp.navigation.routes.NavigationRoutes
import com.example.gameapp.views.DashboardScreen
import com.example.gameapp.views.DetailGameScreen
import com.example.gameapp.views.SplashScreen

/**
 * Login, registration, forgot password screens nav graph builder
 * (Unauthenticated user)
 */
fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.SplashScreen.route
    ) {
        // Splash
        composable(route = NavigationRoutes.Unauthenticated.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
    }
}

/**
 * Authenticated screens nav graph builder
 */
fun NavGraphBuilder.authenticatedGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Authenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Authenticated.DashboardScreen.route
    ) {
        // Dashboard
        composable(route = NavigationRoutes.Authenticated.DashboardScreen.route) {
            DashboardScreen(navController)
        }

        // Detail Game
        composable(
            route = NavigationRoutes.Authenticated.DetailGameScreen.route,
            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
            DetailGameScreen(gameId, navController)
        }
    }
}
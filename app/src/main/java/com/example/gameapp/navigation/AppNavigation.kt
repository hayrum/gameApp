package com.example.gameapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gameapp.views.DashboardScreen
import com.example.gameapp.views.DetailGameScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.DashboardScreen.route
    ) {
        composable(route = Screens.DashboardScreen.route) {
            DashboardScreen(navController = navController)
        }
        composable(route = Screens.DetailGameScreen.route) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")?.toIntOrNull() ?: 0
            DetailGameScreen(gameId = gameId)
        }
    }
}
package com.example.gameapp.navigation

sealed class Screens(val route: String) {
    data object DashboardScreen: Screens("dashboard")
    data object DetailGameScreen: Screens("gameDetail/{gameId}")
}
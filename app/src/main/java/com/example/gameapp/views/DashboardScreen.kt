package com.example.gameapp.views

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gameapp.BuildConfig
import com.example.gameapp.api.RetrofitClient
import com.example.gameapp.api.interfaces.GameServices
import com.example.gameapp.api.repository.GameRepository
import com.example.gameapp.data.Game
import com.example.gameapp.viewmodels.dashboard.DashboardViewModel

@Composable
fun DashboardScreen(navController: NavController) {
    // initialize games
    val gameServices = RetrofitClient.getClient(BuildConfig.API_URL).create(GameServices::class.java)
    val viewModel = remember { DashboardViewModel(GameRepository(gameServices)) }

    val games by viewModel.games.observeAsState(initial = emptyList())
    // get games
    LaunchedEffect(Unit) {
        viewModel.getGames()
    }
    // load games
    LoadInformationGames(games, navController)
}

@Composable
fun LoadInformationGames(listGames: List<Game>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
    ) {
        items(listGames) { game ->
            GameItem(game) {selectGame ->
                navController.navigate(
                    "gameDetail/${selectGame.id}"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(navController = rememberNavController())
}
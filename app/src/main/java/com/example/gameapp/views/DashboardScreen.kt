package com.example.gameapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.gameapp.components.SearchBar
import com.example.gameapp.data.database.GameDatabase
import com.example.gameapp.data.repository.GameDataBaseRepository
import com.example.gameapp.models.Game
import com.example.gameapp.viewmodels.dashboard.DashboardViewModel
import kotlin.text.contains

@Composable
fun DashboardScreen(navController: NavController) {
    // init Dao and ViewModel
    val gameDao = GameDatabase.getDatabase(LocalContext.current.applicationContext).gameDao()
    // remember viewModel
    val viewModel = remember {
        DashboardViewModel(GameDataBaseRepository(gameDao = gameDao))
    }
    LoadView(viewModel, navController)
}

@Composable
fun LoadView(viewModel: DashboardViewModel, navController: NavController) {
    // Variable for text search into search bar.
    var searchText by remember { mutableStateOf("") }
    // Variable for filtering games.
    val filteredGames = getFilteredGames(searchText, viewModel.games.value ?: emptyList())
    // This variable is used to scroll to the top of the list when the filtered games change.
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier.padding(
            top = WindowInsets.statusBars
                .asPaddingValues()
                .calculateTopPadding()
        )
    ) {
        SearchBar(searchText, onSearchTextChange = { searchText = it })
        LoadInformationGames(filteredGames, navController, listState)
    }
    LaunchedEffect(key1 = filteredGames) { // Observe filteredGames
        if (filteredGames.isNotEmpty()) {
            listState.animateScrollToItem(index = 0) // Scroll to top
        }
    }
}

@Composable
fun LoadInformationGames(
    listGames: List<Game>,
    navController: NavController,
    listState: LazyListState
) {
    // Create LazyColumn (recyclerView)
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(listGames) { game ->
            GameItem(game) { selectGame ->
                navController.navigate(
                    "gameDetail/${selectGame.id}"
                )
            }
        }
    }
}

@Composable
fun getFilteredGames(searchText: String, games: List<Game>): List<Game> {
    return remember(searchText, games) {
        derivedStateOf {
            if (searchText.isEmpty()) {
                games // Return all games if search is empty
            } else {
                games.filter { game ->
                    game.title.contains(searchText, ignoreCase = true) ||
                            game.genre.contains(
                                searchText,
                                ignoreCase = true
                            )
                }
            }
        }
    }.value
}

//@Preview(showBackground = true)
//@Composable
//fun DashboardScreenPreview() {
//    DashboardScreen(navController = rememberNavController())
//}
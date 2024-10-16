package com.example.gameapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gameapp.BuildConfig
import com.example.gameapp.api.RetrofitClient
import com.example.gameapp.api.interfaces.GameServices
import com.example.gameapp.api.repository.GameRepository
import com.example.gameapp.data.Game
import com.example.gameapp.viewmodels.dashboard.DetailGameViewModel

@Composable
fun DetailGameScreen(gameId: Int) {
    val viewModel = remember {
        DetailGameViewModel(
            GameRepository(
                RetrofitClient.getClient(BuildConfig.API_URL).create(GameServices::class.java)
            )
        )
    }
    val game = viewModel.game.observeAsState()

    LaunchedEffect(gameId) {
        viewModel.getGameById(gameId)
    }
    LoadDetailGame(game)
}

@Composable
fun LoadDetailGame(game: State<Game?>) {
    val typography = MaterialTheme.typography
    game.value?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
        ) {
            AsyncImage(
                model = it.thumbnail,
                contentDescription = it.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Game Title
            Text(
                text = it.title,
                style = typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text("Description: ${it.short_description}", style = typography.bodyMedium)
            Text("Genre: ${it.genre}", style = typography.bodyMedium)
            Text("Platforms: ${it.platform}", style = typography.bodyMedium)
            Text("Relase date: ${it.release_date}", style = typography.bodyMedium)
            Text("Developer: ${it.developer}", style = typography.bodyMedium)
            Text("Publisher: ${it.publisher}", style = typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailGameScreenPreview() {
    DetailGameScreen(
        gameId = 1
    )
}
package com.example.gameapp.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.gameapp.components.CustomOutlinedTextField
import com.example.gameapp.data.database.GameDatabase
import com.example.gameapp.data.repository.GameDataBaseRepository
import com.example.gameapp.models.Game
import com.example.gameapp.viewmodels.detailgame.DetailGameViewModel

@Composable
fun DetailGameScreen(gameId: Int, navController: NavController) {
    // init Dao and ViewModel
    val gameDao = GameDatabase.getDatabase(LocalContext.current.applicationContext).gameDao()
    val viewModel = remember {
        DetailGameViewModel(
            GameDataBaseRepository(gameDao)
        )
    }
    val game = viewModel.game.observeAsState()

    LaunchedEffect(gameId) {
        viewModel.getGameById(gameId)
    }
    LoadDetailGame(game, viewModel, navController)
}

@Composable
fun LoadDetailGame(
    game: State<Game?>,
    viewModel: DetailGameViewModel,
    navController: NavController
) {
    game.value?.let {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = WindowInsets.statusBars
                        .asPaddingValues()
                        .calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
                .verticalScroll(rememberScrollState())
        ) {
            Card(modifier = Modifier) {
                Column(modifier = Modifier.padding(16.dp)) {
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

                    EditGame(game = it, viewModel, context = LocalContext.current, navController)
                }
            }
        }
    }
}

@Composable
fun EditGame(
    game: Game,
    viewModel: DetailGameViewModel,
    context: Context,
    navController: NavController
) {
    var editedTitle by remember { mutableStateOf(game.title) }
    var editedDescription by remember { mutableStateOf(game.short_description) }
    var editedGenre by remember { mutableStateOf(game.genre) }
    var editedPlatform by remember { mutableStateOf(game.platform) }
    var editedReleaseDate by remember { mutableStateOf(game.release_date) }
    var editedDeveloper by remember { mutableStateOf(game.developer) }
    var editedPublisher by remember { mutableStateOf(game.publisher) }

    game.let { gameData ->
        // Editable fields below the Card
        Column(modifier = Modifier.padding(16.dp)) {
            CustomOutlinedTextField(
                editValue = editedTitle,
                editValueChange = { editedTitle = it },
                titleLabel = "Title",
            )
            CustomOutlinedTextField(
                editValue = editedDescription,
                editValueChange = { editedDescription = it },
                titleLabel = "Description",
            )
            CustomOutlinedTextField(
                editValue = editedGenre,
                editValueChange = { editedGenre = it },
                titleLabel = "Genre",
            )
            CustomOutlinedTextField(
                editValue = editedPlatform,
                editValueChange = { editedPlatform = it },
                titleLabel = "Platform",
            )
            CustomOutlinedTextField(
                editValue = editedReleaseDate,
                editValueChange = { editedReleaseDate = it },
                titleLabel = "Release Date",
            )
            CustomOutlinedTextField(
                editValue = editedDeveloper,
                editValueChange = { editedDeveloper = it },
                titleLabel = "Developer",
            )
            CustomOutlinedTextField(
                editValue = editedPublisher,
                editValueChange = { editedPublisher = it },
                titleLabel = "Publisher",
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    // Update game object with edited values
                    val updatedGame = gameData.copy(
                        title = editedTitle,
                        short_description = editedDescription,
                        genre = editedGenre,
                        platform = editedPlatform,
                        release_date = editedReleaseDate,
                        developer = editedDeveloper,
                        publisher = editedPublisher
                    )
                    viewModel.updateGame(updatedGame)
                    Toast.makeText(context, "Juego actualizado", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(start = 19.dp, end = 19.dp, bottom = 16.dp)
                    .weight(1f),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Editar")
            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun DetailGameScreenPreview() {
//    EditGame(
//        game = Game(
//            id = 1,
//            title = "Game 1",
//            short_description = "Short description 1",
//            genre = "Genre 1",
//            platform = "Platform 1",
//            release_date = "Release date 1",
//            developer = "Developer 1",
//            publisher = "Publisher 1",
//            thumbnail = "https://example.com/thumbnail1.jpg",
//            game_url = "https://example.com/game1.html",
//            freetogame_profile_url = "https://example.com/profile1.html"
//        ),
//        navController = rememberNavController(),
//        viewModel = DetailGameViewModel(
//            GameDataBaseRepository(
//                GameDatabase.getDatabase(LocalContext.current.applicationContext).gameDao()
//            )
//        ),
//        context = LocalContext.current
//    )
//}
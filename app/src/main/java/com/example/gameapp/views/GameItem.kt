package com.example.gameapp.views

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gameapp.models.Game
import com.example.gameapp.viewmodels.dashboard.DashboardViewModel

@Composable
fun GameItem(game: Game, viewModel: DashboardViewModel, onClick: (Game) -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(game) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween, // Add SpaceBetween arrangement
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row( // Wrap image and text in a Row
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = game.thumbnail,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = game.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = game.genre, style = MaterialTheme.typography.bodySmall)
                }
            }

            IconButton(onClick = {
                viewModel.deleteGame(game)
                Toast.makeText(context, "Juego eliminado", Toast.LENGTH_SHORT).show()
            }) { // Delete button
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GameItemPreview() {
//    GameItem(
//        game = Game(
//            541,
//            "Marvel Snap",
//            "https://www.freetogame.com/g/541/thumbnail.jpg",
//            "A fast paced strategy card game set in the Marvel universe.",
//            "https://www.freetogame.com/open/marvel-snap",
//            "Card Game",
//            "PC (Windows)",
//            "Nuverse",
//            "Second Dinner Studios, Inc.",
//            "2022-10-18",
//            "https://www.freetogame.com/marvel-snap"
//        ),
//        viewModel = viewModel(),
//        onClick = {}
//    )
//}
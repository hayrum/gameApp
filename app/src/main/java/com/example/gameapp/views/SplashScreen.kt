package com.example.gameapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gameapp.BuildConfig
import com.example.gameapp.R
import com.example.gameapp.api.RetrofitClient
import com.example.gameapp.api.interfaces.GameServices
import com.example.gameapp.api.repository.GameRepository
import com.example.gameapp.data.database.GameDatabase
import com.example.gameapp.data.repository.GameDataBaseRepository
import com.example.gameapp.navigation.routes.NavigationRoutes
import com.example.gameapp.viewmodels.splash.SplashViewModel

@Composable
fun SplashScreen(navController: NavController) {
    val gameServices =
        RetrofitClient.getClient(BuildConfig.API_URL).create(GameServices::class.java)
    val gameDao = GameDatabase.getDatabase(LocalContext.current.applicationContext).gameDao()
    val viewModel = remember {
        SplashViewModel(
            GameRepository(gameServices),
            GameDataBaseRepository(gameDao = gameDao)
        )
    }
    val navigateToDashboard by viewModel.navigateToDashboard.observeAsState(initial = false)

    LaunchedEffect(key1 = Unit) {
        viewModel.getGames()
    }

    LaunchedEffect(key1 = navigateToDashboard) {
        if (navigateToDashboard) {
            // TODO: (only for testing this 2 lines)
            //val numberOfRecords = gameDao.getGamesCount().first() // Get the count
            //Log.d("DatabaseData", "Number of records: $numberOfRecords")
            // TODO: (only for testing this 2 lines)
            navController.navigate(NavigationRoutes.Authenticated.DashboardScreen.route)
        }
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo",
            modifier = Modifier
                .size(width = 200.dp, height = 200.dp)
                .clip(RoundedCornerShape(topEnd = 100.dp, bottomStart = 100.dp))
        )
        Text(
            text = "Game App",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SplashPreview() {
//    Splash()
//}
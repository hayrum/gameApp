package com.example.gameapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gameapp.navigation.AppNavigation
import com.example.gameapp.ui.theme.GameAppTheme
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameAppTheme {
                AppNavigation()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GameAppTheme {
        AppNavigation()
    }
}
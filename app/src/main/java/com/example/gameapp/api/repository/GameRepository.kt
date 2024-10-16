package com.example.gameapp.api.repository

import com.example.gameapp.api.interfaces.GameServices
import com.example.gameapp.data.Game
import retrofit2.awaitResponse

class GameRepository (private val service: GameServices) {

    suspend fun getGames(): List<Game> {
        return service.getAllGames().awaitResponse().body()!!
    }

    suspend fun getGameById(id: Int): Game {
        return service.getGameById(id).awaitResponse().body()!!
    }
}
package com.example.gameapp.data.repository

import com.example.gameapp.data.database.dao.GameDao
import com.example.gameapp.data.database.entities.EntityGame
import kotlinx.coroutines.flow.first

class GameDataBaseRepository(
    private val gameDao: GameDao
) {
    suspend fun checkIfGamesAreSaved(): Boolean {
        return gameDao.getAllGames().first().isNotEmpty()
    }
    suspend fun insertGames(games: List<EntityGame>) {
        gameDao.insertAll(games)
    }
}
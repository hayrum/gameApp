package com.example.gameapp.data.repository

import com.example.gameapp.data.database.dao.GameDao
import com.example.gameapp.data.database.entities.EntityGame
import kotlinx.coroutines.flow.Flow
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

    fun getAllGames(): Flow<List<EntityGame>> {
        return gameDao.getAllGames()
    }

    fun getGameById(id: Int): Flow<EntityGame> {
        return gameDao.getGameById(id)
    }

    suspend fun updateGame(game: EntityGame) {
        gameDao.updateGame(game)
    }

    suspend fun deleteGame(game: EntityGame) {
        gameDao.deleteGame(game)
    }

}
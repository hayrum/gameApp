package com.example.gameapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gameapp.data.database.entities.EntityGame
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<EntityGame>)

    @Update
    suspend fun updateGame(game: EntityGame)

    @Delete
    suspend fun deleteGame(game: EntityGame)

    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<EntityGame>>

    @Query("SELECT COUNT(*) FROM games")
    fun getGamesCount(): Flow<Int>

    @Query("SELECT * FROM games WHERE id = :gameId")
    fun getGameById(gameId: Int): Flow<EntityGame>

}
package com.example.gameapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gameapp.data.database.entities.EntityGame
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<EntityGame>)

    @Delete
    fun deleteGame(game: EntityGame)

    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<EntityGame>>

    @Query("SELECT COUNT(*) FROM games")
    fun getGamesCount(): Flow<Int>

}
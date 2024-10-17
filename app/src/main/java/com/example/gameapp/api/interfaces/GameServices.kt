package com.example.gameapp.api.interfaces

import com.example.gameapp.models.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GameServices {
    // Return the list of games
    @GET("api/games")
    fun getAllGames(): Call<List<Game>>
    @GET("/api/game")
    fun getGameById(@Query("id") id: Int): Call<Game>
}
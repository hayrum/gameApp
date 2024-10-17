package com.example.gameapp.viewmodels.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.api.repository.GameRepository
import com.example.gameapp.data.mappers.GameMapper
import com.example.gameapp.data.repository.GameDataBaseRepository
import com.example.gameapp.models.Game
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: GameRepository,
    private val gameDatabaseRepository: GameDataBaseRepository
) : ViewModel() {
    private val _games = MutableLiveData<List<Game>>()
    val games: MutableLiveData<List<Game>>
        get() = _games

    private val _navigateToDashboard = MutableLiveData<Boolean>()
    val navigateToDashboard: LiveData<Boolean> = _navigateToDashboard

    private val gameMapper = GameMapper()

    fun getGames() {
        viewModelScope.launch {
            try {
                // Verify if exist data in my database of games
                if (gameDatabaseRepository.checkIfGamesAreSaved()) {
                    _navigateToDashboard.value = true
                } else { // no exist data
                    val apiGames = repository.getGames()
                    _games.value = apiGames
                    // Saving games to database
                    gameDatabaseRepository.insertGames(gameMapper.mapGamesToEntities(games))
                    _navigateToDashboard.value = true
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
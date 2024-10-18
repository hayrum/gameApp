package com.example.gameapp.viewmodels.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.data.mappers.GameMapper
import com.example.gameapp.data.repository.GameDataBaseRepository
import com.example.gameapp.models.Game
import kotlinx.coroutines.launch

class DashboardViewModel(private val gameDatabaseRepository: GameDataBaseRepository) :
    ViewModel() {
    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    private val gameMapper = GameMapper()

    init {
        getGamesFromDatabase()
    }

    private fun getGamesFromDatabase() {
        viewModelScope.launch {
            gameDatabaseRepository.getAllGames().collect { gameEntities ->
                _games.value = gameMapper.mapEntitiesToGames(gameEntities)
            }
        }
    }
}
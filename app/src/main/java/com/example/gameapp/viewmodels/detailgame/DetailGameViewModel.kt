package com.example.gameapp.viewmodels.detailgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.data.mappers.GameMapper
import com.example.gameapp.data.repository.GameDataBaseRepository
import com.example.gameapp.models.Game
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetailGameViewModel(private val gameRepository: GameDataBaseRepository) : ViewModel() {

    private val _game = MutableLiveData<Game>()
    val game: MutableLiveData<Game>
        get() = _game

    private val gameMapper = GameMapper()

    fun getGameById(gameId: Int) {
        viewModelScope.launch {
            try {
                val game = gameRepository.getGameById(gameId)
                _game.value = gameMapper.mapEntityToGame(game.first())
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun updateGame(updateGame: Game) {
        viewModelScope.launch {
            gameRepository.updateGame(gameMapper.mapGameToEntity(updateGame))
        }
    }
}
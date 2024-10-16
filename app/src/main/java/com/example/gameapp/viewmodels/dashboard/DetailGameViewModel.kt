package com.example.gameapp.viewmodels.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.api.repository.GameRepository
import com.example.gameapp.data.Game
import kotlinx.coroutines.launch

class DetailGameViewModel(private val gameRepository: GameRepository) : ViewModel() {

    private val _game = MutableLiveData<Game>()
    val game: MutableLiveData<Game>
        get() = _game

    fun getGameById(gameId: Int) {
        viewModelScope.launch {
            try {
                val game = gameRepository.getGameById(gameId)
                _game.value = game
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
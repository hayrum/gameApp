package com.example.gameapp.viewmodels.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.api.repository.GameRepository
import com.example.gameapp.data.Game
import kotlinx.coroutines.launch

class DashboardViewModel (
    private val repository: GameRepository
) : ViewModel() {
    private val _games = MutableLiveData<List<Game>>()
    val games: MutableLiveData<List<Game>>
        get() = _games

    fun getGames() {
        viewModelScope.launch {
            try {
                _games.value = repository.getGames()
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
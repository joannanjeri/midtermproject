package com.example.midtermproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * viewmodel to hold and manage ui-related data for the game lifecycle.
 * stores the number of attempts and the game finished state.
 */
class GameViewModel : ViewModel() {
    private val _attempts = MutableLiveData(0)
    val attempts: LiveData<Int> get() = _attempts

    private val _gameFinished = MutableLiveData<Pair<String, Int>?>(null)
    val gameFinished: LiveData<Pair<String, Int>?> get() = _gameFinished

    /**
     * increments the attempt count by 1.
     */
    fun incrementAttempts() {
        _attempts.value = (_attempts.value ?: 0) + 1
    }

    /**
     * resets the attempt count to 0.
     */
    fun resetAttempts() {
        _attempts.value = 0
    }

    /**
     * marks the game as finished with the player's name and score.
     */
    fun gameFinished(playerName: String, score: Int) {
        _gameFinished.value = Pair(playerName, score)
    }

    /**
     * resets the game finished state.
     */
    fun resetGameFinished() {
        _gameFinished.value = null
    }
}
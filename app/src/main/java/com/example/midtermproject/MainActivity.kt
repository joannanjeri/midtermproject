package com.example.midtermproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
/**
 * main activity for the game, handling ui and navigation.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var gameViewModel: GameViewModel
    private lateinit var welcomeTextView: TextView
    private lateinit var gameFinishedLayout: LinearLayout
    private lateinit var scoreTextView: TextView
    private lateinit var fragmentContainer: FrameLayout

    /**
     * sets up the activity, initializes ui components, and sets click listeners.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        welcomeTextView = findViewById(R.id.welcomeTV)
        gameFinishedLayout = findViewById(R.id.gameFinishedLayout)
        scoreTextView = findViewById(R.id.scoreTextView)
        fragmentContainer = findViewById(R.id.fragmentContainer)

        val playButton: Button = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            if (gameFinishedLayout.visibility == View.VISIBLE) {
                resetGame()
            } else {
                startGame()
            }
        }

        val highScoreButton: View = findViewById(R.id.highScoreButton)
        highScoreButton.setOnClickListener {
        }

        gameViewModel.gameFinished.observe(this) { gameFinishInfo ->
            gameFinishInfo?.let { (playerName, score) ->
                updateScore(playerName, score)
                gameViewModel.resetGameFinished()
            }
        }
    }

    /**
     * starts the game, setting up necessary data and ui components.
     */
    private fun startGame() {
        gameViewModel.resetAttempts()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, Fragment1())
            .addToBackStack(null)
            .commit()
    }

    /**
     * resets the game to its initial state.
     */
    private fun resetGame() {
        gameViewModel.resetAttempts()
        gameViewModel.resetGameFinished()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, Fragment1())
            .commit()

        gameFinishedLayout.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE
        welcomeTextView.text = getString(R.string.welcome_to_the_game)
    }

    /**
     * updates the score display with the player's name and score.
     */
    fun updateScore(playerName: String, score: Int) {
        scoreTextView.text = "$playerName score: $score"
        gameFinishedLayout.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE
        welcomeTextView.text = getString(R.string.play_another_game_prompt)
    }
}



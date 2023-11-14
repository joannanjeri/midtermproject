package com.example.midtermproject

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
/**
 * fragment representing the first screen of the game where the user can make a guess.
 * holds a random number and media player for game interactions.
 */
class Fragment1 : Fragment() {
    private var randomNumber = (1..100).random()
    private var mediaPlayer: MediaPlayer? = null

    private lateinit var playerNameEditText: EditText
    private lateinit var guessEditText: EditText
    private val gameViewModel: GameViewModel by activityViewModels()

    // inflates the layout for the fragment and initializes ui components
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment1_l, container, false)

        playerNameEditText = view.findViewById(R.id.playerNameEditText)
        guessEditText = view.findViewById(R.id.guessEditText)
        val minusButton: TextView = view.findViewById(R.id.minusButton)
        val plusButton: TextView = view.findViewById(R.id.plusButton)
        val okButton: TextView = view.findViewById(R.id.okButton)

        minusButton.setOnClickListener { decrementGuess() }
        plusButton.setOnClickListener { incrementGuess() }
        okButton.setOnClickListener { checkGuess() }

        mediaPlayer = MediaPlayer.create(context, R.raw.wrong_sound)

        return view
    }

    // increments the guess number in the guessEditText field
    private fun incrementGuess() {
        val currentGuess = guessEditText.text.toString().toIntOrNull() ?: 0
        guessEditText.setText((currentGuess + 1).toString())
    }

    // decrements the guess number in the guessEditText field
    private fun decrementGuess() {
        val currentGuess = guessEditText.text.toString().toIntOrNull() ?: 0
        if (currentGuess > 1) {
            guessEditText.setText((currentGuess - 1).toString())
        }
    }

    // checks the user's guess against the random number
    private fun checkGuess() {
        val guess = guessEditText.text.toString().toIntOrNull()
        guess?.let {
            gameViewModel.incrementAttempts()
            when {
                it == randomNumber -> {
                    val playerName = playerNameEditText.text.toString().ifEmpty { "Player" }
                    storeScore(playerName, gameViewModel.attempts.value ?: 0)
                    gameViewModel.gameFinished(playerName, gameViewModel.attempts.value ?: 0)
                    mediaPlayer?.release()
                    mediaPlayer = null
                    activity?.supportFragmentManager?.popBackStack()
                }
                it < randomNumber -> {
                    Toast.makeText(context, "Higher", Toast.LENGTH_SHORT).show()
                    playSound()
                }
                else -> {
                    Toast.makeText(context, "Lower", Toast.LENGTH_SHORT).show()
                    playSound()
                }
            }
        }
    }

    // stores the player's score in the database
    private fun storeScore(playerName: String, score: Int) {
        runBlocking(Dispatchers.IO) {
            val scoreDao = AppDatabase.getDatabase(requireContext()).scoreDao()
            scoreDao.insert(Score(playerName = playerName, score = score))
        }
    }

    // plays a sound effect for feedback
    private fun playSound() {
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
        }
    }

    // displays the game result on the ui
    private fun showResult(playerName: String, score: Int) {
        activity?.runOnUiThread {
            if (activity is MainActivity) {
                (activity as MainActivity).updateScore(playerName, score)
            }
        }
    }

    // releases the media player resources when the view is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

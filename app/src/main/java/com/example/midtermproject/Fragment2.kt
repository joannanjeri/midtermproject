//package com.example.midtermproject
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
//import org.w3c.dom.Text
//
//class Fragment2 : Fragment() {
//    private lateinit var scoreTextView: TextView
//    private val gameViewModel: GameView by activityViewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment2_l, container, false)
//
//        scoreTextView = view.findViewById(R.id.scoreTextView)
//        gameViewModel.attempts.observe(viewLifecycleOwner) { attempts ->
//            scoreTextView.text = "Number of attempts: $attempts"
//        }
//
//        gameViewModel.gameFinished.observe(viewLifecycleOwner) { gameFinishInfo ->
//            gameFinishInfo?.let { (playerName, score) ->
//                // Update the UI with the score
//                scoreTextView.text = "$playerName score: $score"
//                // Inform the main activity to update its UI
//                (activity as MainActivity).updateScore(playerName, score)
//                // Reset the game finished event in the ViewModel
//                gameViewModel.resetGameFinished()
//            }
//        }
//
//        return view
//    }
//}
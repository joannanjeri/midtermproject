//package com.example.midtermproject
//
//import android.os.Bundle
//import android.view.View
//import android.widget.TextView
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class HighScoreActivity : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var noScoresTextView: TextView
//    private lateinit var adapter: ScoreAdapter
//    private val gameViewModel: GameViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.high_score)
//
//        recyclerView = findViewById(R.id.highScoresRecyclerView)
//        noScoresTextView = findViewById(R.id.noScoresTextView)
//
//        adapter = ScoreAdapter(emptyList()) { score ->
//            val dialog = DeleteConfirmationDialogFragment(score) {
//                gameViewModel.deleteScore(score)
//            }
//            dialog.show(supportFragmentManager, "confirmDelete")
//        }
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        gameViewModel.scores.observe(this) { scores ->
//            if (scores.isEmpty()) {
//                noScoresTextView.visibility = View.VISIBLE
//            } else {
//                noScoresTextView.visibility = View.GONE
//                adapter.updateScores(scores)
//            }
//        }
//    }
//}

//package com.example.midtermproject
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageButton
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class ScoreAdapter(private var scores: List<Score>, private val onDeleteClick: (Score) -> Unit) :
//    RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {
//
//    class ScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val nameTextView: TextView = view.findViewById(R.id.playerNameTextView)
//        val scoreTextView: TextView = view.findViewById(R.id.scoreTextView)
//        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.score_item, parent, false)
//        return ScoreViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
//        val score = scores[position]
//        holder.nameTextView.text = score.playerName
//        holder.scoreTextView.text = "Guesses: ${score.score}"
//        holder.deleteButton.setOnClickListener {
//            onDeleteClick(score)
//        }
//    }
//
//    override fun getItemCount() = scores.size
//
//    fun updateScores(newScores: List<Score>) {
//        scores = newScores
//        notifyDataSetChanged()
//    }
//}

package com.example.midtermproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * entity representing a score in the game.
 */
@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "player_name") val playerName: String,
    @ColumnInfo(name = "score") val score: Int
)
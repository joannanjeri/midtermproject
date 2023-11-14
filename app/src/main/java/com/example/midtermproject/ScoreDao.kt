package com.example.midtermproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {
    @Insert
    suspend fun insert(score: Score): Long

    @Query("SELECT * FROM scores ORDER BY score DESC")
    fun getAllScores(): LiveData<List<Score>>

    @Delete
    suspend fun delete(score: Score)
}
package com.example.midtermproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
/**
 * represents a room database for the application, holding the score entities.
 */
@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     * gets the dao for score entities.
     */
    abstract fun scoreDao(): ScoreDao

    /**
     * companion object to provide a singleton instance of the database.
     */
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * returns the singleton instance of the database, creating it if it doesn't exist.
         * uses a synchronized block to ensure only one instance is created.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "game_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
package com.example.brawlmobile.brawlStar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.brawlmobile.brawlStar.data.dao.FavouriteBrawlerDao
import com.example.brawlmobile.brawlStar.data.entities.FavouriteBrawlerEntity

@Database(entities = [FavouriteBrawlerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteBrawlerDao(): FavouriteBrawlerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "brawler_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}
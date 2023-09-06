package com.example.brawlmobile.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.brawlmobile.data.dao.BrawlerDao
import com.example.brawlmobile.data.dao.CardDao
import com.example.brawlmobile.data.entities.BrawlerEntity
import com.example.brawlmobile.data.entities.CardEntity

@Database(entities = [BrawlerEntity::class, CardEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteBrawlerDao(): BrawlerDao
    abstract fun favouriteCardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }

        }
    }
}
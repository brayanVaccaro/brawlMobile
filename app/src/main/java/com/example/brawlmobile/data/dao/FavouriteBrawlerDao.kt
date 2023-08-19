package com.example.brawlmobile.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.brawlmobile.data.entities.FavouriteBrawlerEntity

@Dao
interface FavouriteBrawlerDao {
    @Query("SELECT * FROM favourite_brawlers")
    fun deleteFavouriteBrawler(): LiveData<List<FavouriteBrawlerEntity>>

    @Upsert
    suspend fun insertFavouriteBrawler(brawler: FavouriteBrawlerEntity)
}
package com.example.brawlmobile.brawlStar.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.brawlmobile.brawlStar.data.entities.FavouriteBrawlerEntity

@Dao
interface FavouriteBrawlerDao {
    @Query("SELECT * FROM favourite_brawlers")
    fun getAllFavouriteBrawlers(): LiveData<List<FavouriteBrawlerEntity>>

    @Query("DELETE FROM favourite_brawlers WHERE name = :name")
    fun deleteByName(name: String)

    @Upsert
    suspend fun insertFavouriteBrawler(brawler: FavouriteBrawlerEntity)
}
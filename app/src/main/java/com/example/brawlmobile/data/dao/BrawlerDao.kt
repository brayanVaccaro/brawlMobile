package com.example.brawlmobile.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.brawlmobile.data.entities.BrawlerEntity

@Dao
interface BrawlerDao {
    @Query("SELECT * FROM favourite_brawlers")
    fun getAllBrawlers(): LiveData<List<BrawlerEntity>>

    @Query("DELETE FROM favourite_brawlers WHERE name = :name")
    fun deleteByName(name: String)

    @Upsert
    suspend fun insertBrawler(brawler: BrawlerEntity)
}
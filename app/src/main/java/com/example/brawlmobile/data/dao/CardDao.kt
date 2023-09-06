package com.example.brawlmobile.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.brawlmobile.data.entities.BrawlerEntity
import com.example.brawlmobile.data.entities.CardEntity

@Dao
interface CardDao {
    @Query("SELECT * FROM favourite_cards")
    fun getAllCards(): LiveData<List<CardEntity>>

    @Query("DELETE FROM favourite_cards WHERE name = :name")
    fun deleteByName(name: String)

    @Upsert
    suspend fun insertCard (card: CardEntity)
}
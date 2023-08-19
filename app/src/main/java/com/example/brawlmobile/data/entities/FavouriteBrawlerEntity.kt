package com.example.brawlmobile.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "favourite_brawlers")
data class FavouriteBrawlerEntity(
    @PrimaryKey val id: String,
    val name: String,
    val spriteUrl: String?
)
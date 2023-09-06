package com.example.brawlmobile.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_cards")
data class CardEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val spriteUrl: String?
)
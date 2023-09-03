package com.example.brawlmobile.model.clashRoyale

data class CardModel(
    val name: String,
    val id: Int,
    val maxLevel: Int,
    val maxEvolutionLevel: Int?,
    val iconUrls: IconUrls
)

data class IconUrls (
    val medium: String,
    val evolutionMedium: String?
)
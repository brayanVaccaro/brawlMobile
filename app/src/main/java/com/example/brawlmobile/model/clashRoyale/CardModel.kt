package com.example.brawlmobile.model.clashRoyale

data class CardModel(
    var name: String,
    val id: Int,
    val maxLevel: Int,
    val maxEvolutionLevel: Int?,
    val iconUrls: IconUrls?,
    val imageUrl: String?,
    var transformedName: String?
)

data class IconUrls (
    val medium: String,
    val evolutionMedium: String?
)
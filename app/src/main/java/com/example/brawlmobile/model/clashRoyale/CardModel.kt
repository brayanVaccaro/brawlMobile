package com.example.brawlmobile.model.clashRoyale

data class CardModel(
    val id: Int,
    var name: String,
    var transformedName: String,
    val maxLevel: Int,
    val maxEvolutionLevel: Int?,
    val urlNormal: String,
    val urlEvolution: String
)
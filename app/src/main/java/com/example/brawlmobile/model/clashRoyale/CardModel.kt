package com.example.brawlmobile.model.clashRoyale

data class CardModel(
    var name: String,
    var transformedName: String,
    val maxLevel: Int,
    val maxEvolutionLevel: Int?,
    val urlNormal: String,
    val urlEvolution: String
)
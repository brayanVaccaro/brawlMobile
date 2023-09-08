package com.example.brawlmobile.remote.clashRoyale.model

class CardResponseModel(
    var name: String,
    var transformedName: String?,
    val id: Int,
    val maxLevel: Int,
    val maxEvolutionLevel: Int?,
    val iconUrls: IconUrls,
)

data class IconUrls(
    val medium: String,
    val evolutionMedium: String?
)
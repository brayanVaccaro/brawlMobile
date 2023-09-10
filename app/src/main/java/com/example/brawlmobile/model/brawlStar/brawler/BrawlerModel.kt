package com.example.brawlmobile.model.brawlStar.brawler

data class BrawlerModel(
    val id: Int,
    var name: String,
    var transformedName: String,
    val firstStarPower: String,
    val secondStarPower: String,
    val firstGadget: String,
    val secondGadget: String,
    val spriteUrl: String?
)
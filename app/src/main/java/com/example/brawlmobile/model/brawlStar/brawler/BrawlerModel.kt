package com.example.brawlmobile.model.brawlStar.brawler

data class BrawlerModel(
    val id: Int,
    var name: String,
    val starPowers: List<StarPower>,
    val gadgets: List<Gadget>,
    val spriteUrl: String?
)
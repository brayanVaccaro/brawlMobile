package com.example.brawlmobile.remote.brawlStars.model

import com.example.brawlmobile.model.brawlStar.brawler.Gadget
import com.example.brawlmobile.model.brawlStar.brawler.StarPower

data class BrawlerResponseModel (
    val id: Int,
    var name: String,
    var transformedName: String?,
    val starPowers: List<StarPower>,
    val gadgets: List<Gadget>,
    val spriteUrl: String?
)
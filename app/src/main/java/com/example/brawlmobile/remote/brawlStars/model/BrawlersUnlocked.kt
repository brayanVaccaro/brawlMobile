package com.example.brawlmobile.remote.brawlStars.model

import com.example.brawlmobile.model.brawlStar.brawler.Gadget
import com.example.brawlmobile.model.brawlStar.brawler.Gear
import com.example.brawlmobile.model.brawlStar.brawler.StarPower

data class BrawlersUnlocked(
    val id: Int,
    var name: String,
    val power: Int,
    val rank: Int,
    val trophies: Int,
    val highestTrophies: Int,
    val gears: List<Gear>,
    val starPowers: List<StarPower>,
    val gadgets: List<Gadget>,
)
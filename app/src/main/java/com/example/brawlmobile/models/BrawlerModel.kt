package com.example.brawlmobile.models

data class BrawlerModel(
    val id: Int,
    var name: String,
    val starPowers: List<StarPower>,
    val gadgets: List<Gadget>
)
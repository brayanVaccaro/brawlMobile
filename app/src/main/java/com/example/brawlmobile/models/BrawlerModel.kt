package com.example.brawlmobile.models

data class BrawlerModel(
    val id: Int,
    val name: String,
    val starPowers: List<StarPower>,
    val gadgets: List<Gadget>
)
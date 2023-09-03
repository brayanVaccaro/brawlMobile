package com.example.brawlmobile.brawlStar.remote.model

import com.example.brawlmobile.brawlStar.models.brawler.Gadget
import com.example.brawlmobile.brawlStar.models.brawler.Gear
import com.example.brawlmobile.brawlStar.models.brawler.StarPower
import com.squareup.moshi.Json

data class PlayerInfoResponse(
    val tag: String,
    val name: String,
    val nameColor: String,
    val icon: BrawlerIcon,
    val trophies: Int,
    val highestTrophies: Int,
    val expLevel: Int,
    val expPoints: Int,
    val isQualifiedFromChampionshipChallenge: Boolean,
    @Json(name = "3vs3Victories") val threeVsThreeVictories: Int,
    val soloVictories: Int,
    val duoVictories: Int,
    val bestRoboRumbleTime: Int,
    val bestTimeAsBigBrawler: Int,
    val club: Club,
    val brawlers: List<BrawlersUnlocked>
)

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

data class BrawlerIcon(
    val id: Int
)

data class Club(
    val id: Int?
)
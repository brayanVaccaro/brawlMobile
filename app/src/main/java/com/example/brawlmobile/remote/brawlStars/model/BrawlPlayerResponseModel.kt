package com.example.brawlmobile.remote.brawlStars.model

import com.squareup.moshi.Json

data class BrawlPlayerResponseModel(
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
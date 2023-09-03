package com.example.brawlmobile.brawlStar.models.player

import com.example.brawlmobile.brawlStar.remote.model.BrawlerIcon
import com.example.brawlmobile.brawlStar.remote.model.BrawlersUnlocked
import com.squareup.moshi.Json

data class PlayerInfoModel(
    val tag: String,
    val name: String,
    val nameColor: String,
    val icon: BrawlerIcon,
    val trophies: Int,
    val highestTrophies: Int,
    val expLevel: Int,
    val expPoints: Int,
    val isQualifiedFromChampionshipChallenge: Boolean,
    @field:Json(name = "3vs3Victories")
    val threeVsThreeVictories: Int,
    val soloVictories: Int,
    val duoVictories: Int,
    val bestRoboRumbleTime: Int,
    val bestTimeAsBigBrawler: Int,
    val brawlersUnlocked: List<BrawlersUnlocked>
)
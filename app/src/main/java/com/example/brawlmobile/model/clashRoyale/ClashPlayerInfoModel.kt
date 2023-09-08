package com.example.brawlmobile.model.clashRoyale

import com.example.brawlmobile.remote.clashRoyale.model.*

data class ClashPlayerInfoModel (

    val tag: String,
    val name: String,
    val expLevel: Int,
    val trophies: Int,
    val bestTrophies: Int,
    val wins: Int,
    val losses: Int,
    val battleCount: Int,
    val threeCrownWins: Int,
    val challengeCardsWon: Int,
    val challengeMaxWins: Int,
    val tournamentCardsWon: Int,
    val tournamentBattleCount: Int,
    val role: String,
    val donations: Int,
    val donationsReceived: Int,
    val totalDonations: Int,
    val warDayWins: Int,
    val clanCardsCollected: Int,
    val clan: Clan?,
    val arena: Arena,
    val leagueStatistics: LeagueStatistics,
    val badges: List<Badge>,
    val achievements: List<Achievements>,
    val cards: List<Card>,
    val currentDeck: List<Card>,
    val currentFavouriteCard: Card,
    val starPoints: Int,
    val expPoints: Int,
    val legacyTrophyRoadHighScore: Int?,
    val currentPathOfLegendSeasonResult: PathOfLegendSeasonResult?,
    val lastPathOfLegendSeasonResult: PathOfLegendSeasonResult?,
    val bestPathOfLegendSeasonResult: PathOfLegendSeasonResult?,
    val totalExpPoints: Int,
)
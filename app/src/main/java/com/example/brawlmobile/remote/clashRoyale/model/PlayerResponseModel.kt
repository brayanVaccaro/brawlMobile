package com.example.brawlmobile.remote.clashRoyale.model

data class PlayerResponseModel(
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
    val clan: Clan,
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

data class Clan(

    val tag: String,
    val name: String,
    val badgeId: Int
)

data class Arena(
    val id: Int,
    val name: String,
)

data class LeagueStatistics(
    val currentSeason: CurrentSeason,
    val previousSeason: PreviousSeason,
    val bestSeason: BestSeason
)

data class CurrentSeason(
    val trophies: Int,
    val bestTrophies: Int
)

data class PreviousSeason(
    val id: String,
    val trophies: Int,
    val bestTrophies: Int
)

data class BestSeason(
    val id: String,
    val rank: Int,
    val trophies: Int
)

data class Badge(
    val name: String,
    val level: Int?,
    val maxLevel: Int?,
    val progress: Int?,
    val target: Int?,
    val iconUrls: BadgeIconUrls
)

data class BadgeIconUrls(
    val large: String
)

data class Achievements(
    val name: String,
    val stars: Int,
    val value: Int,
    val target: Int,
    val info: String,
    val completionInfo: Boolean?
)

data class Card(
    val name: String,
    val id: Int,
    val level: Int?,
    val maxLevel: Int?,
    val count: Int?,
    val iconUrls: IconUrls
)

data class PathOfLegendSeasonResult(
    val leagueNumber: Int,
    val trophies: Int,
    val rank: Int?
)
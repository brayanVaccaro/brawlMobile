package com.example.brawlmobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.model.brawlStar.player.PlayerInfoModel
import com.example.brawlmobile.model.clashRoyale.ClashPlayerInfoModel
import com.example.brawlmobile.remote.brawlStars.model.BrawlersUnlocked
import com.example.brawlmobile.remote.clashRoyale.model.Badge
import com.example.brawlmobile.remote.clashRoyale.model.Card
import com.example.brawlmobile.repository.brawlStars.player.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayerActivityViewModel(
    context: Context
) : ViewModel() {

    private val playerRepository: PlayerRepository

    private val TAG = "PlayerViewModel"

    init {
        playerRepository = PlayerRepository(context)

    }

    val brawlPlayerInfo: MutableLiveData<PlayerInfoModel> by lazy {
        MutableLiveData<PlayerInfoModel>()
    }
    val clashPlayerInfo: MutableLiveData<ClashPlayerInfoModel> by lazy {
        MutableLiveData<ClashPlayerInfoModel>()
    }
    val playerBrawlersUnlocked: MutableLiveData<List<BrawlersUnlocked>> by lazy {
        MutableLiveData<List<BrawlersUnlocked>>()
    }
    val playerCardsUnlocked: MutableLiveData<List<Card>> by lazy {
        MutableLiveData<List<Card>>()
    }
    val playerBadgesUnlocked: MutableLiveData<List<Badge>> by lazy {
        MutableLiveData<List<Badge>>()
    }
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getBrawlPlayerInfo(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "recupero i dati del player, Brawl Stars")
            try {
                val playerInfoFlow = playerRepository.fetchBrawlPlayerInfo(tag)
                playerInfoFlow.collect {
                    Log.d(TAG, "sono nel collect")
                    val uiPlayerInfo = PlayerInfoModel(
                        tag = it.tag,
                        name = it.name,
                        nameColor = it.nameColor,
                        icon = it.icon,
                        trophies = it.trophies,
                        highestTrophies = it.highestTrophies,
                        expLevel = it.expLevel,

                        expPoints = it.expPoints,
                        isQualifiedFromChampionshipChallenge = it.isQualifiedFromChampionshipChallenge,
                        threeVsThreeVictories = it.threeVsThreeVictories,
                        soloVictories = it.soloVictories,
                        duoVictories = it.duoVictories,
                        bestRoboRumbleTime = it.bestRoboRumbleTime,
                        bestTimeAsBigBrawler = it.bestTimeAsBigBrawler,
                        brawlersUnlocked = it.brawlers
                    )
                    val uiPlayerBrawlersUnlocked = uiPlayerInfo.brawlersUnlocked
//                    Log.d(TAG, "uiPlayerBrawlerUnlocked vale = $uiPlayerBrawlersUnlocked")

                    withContext(Dispatchers.IO) {
                        Log.d(TAG, "sto facendo postvalue")
                        brawlPlayerInfo.postValue(uiPlayerInfo)
                        playerBrawlersUnlocked.postValue(uiPlayerBrawlersUnlocked)
                        errorLiveData.postValue("")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorLiveData.postValue("${e.message}")
                }
            }
        }

    }

    fun getClashPlayerInfo(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "recupero i dati del player, Clash Royale")
            try {
                val playerInfoFlow = playerRepository.fetchClashPlayerInfo(tag)
                playerInfoFlow.collect {
                    Log.d(TAG, "sono nel collect")
                    val uiPlayerInfo = ClashPlayerInfoModel(
                        tag = it.tag,
                        name = it.name,
                        expLevel = it.expLevel,
                        trophies = it.trophies,
                        bestTrophies = it.bestTrophies,
                        wins = it.wins,
                        losses = it.losses,
                        battleCount = it.battleCount,
                        threeCrownWins = it.threeCrownWins,
                        challengeCardsWon = it.challengeCardsWon,
                        challengeMaxWins = it.challengeMaxWins,
                        tournamentCardsWon = it.tournamentCardsWon,
                        tournamentBattleCount = it.tournamentBattleCount,
                        role = it.role,
                        donations = it.donations,
                        donationsReceived = it.donationsReceived,
                        totalDonations = it.totalDonations,
                        warDayWins = it.warDayWins,
                        clanCardsCollected = it.clanCardsCollected,
                        clan = it.clan,
                        arena = it.arena,
                        leagueStatistics = it.leagueStatistics,
                        badges = it.badges,
                        achievements = it.achievements,
                        cards = it.cards,
                        currentDeck = it.currentDeck,
                        currentFavouriteCard = it.currentFavouriteCard,
                        starPoints = it.starPoints,
                        expPoints = it.expPoints,
                        legacyTrophyRoadHighScore = it.legacyTrophyRoadHighScore,
                        currentPathOfLegendSeasonResult = it.currentPathOfLegendSeasonResult,
                        lastPathOfLegendSeasonResult = it.lastPathOfLegendSeasonResult,
                        bestPathOfLegendSeasonResult = it.bestPathOfLegendSeasonResult,
                        totalExpPoints = it.totalExpPoints

                    )
                    val uiPlayerCardsUnlocked = uiPlayerInfo.cards
                    val uiPlayerBadgesUnlocked = uiPlayerInfo.badges

                    withContext(Dispatchers.IO) {
                        Log.d(TAG, "sto facendo postvalue")
                        clashPlayerInfo.postValue(uiPlayerInfo)
                        playerCardsUnlocked.postValue(uiPlayerCardsUnlocked)
                        playerBadgesUnlocked.postValue(uiPlayerBadgesUnlocked)

                        errorLiveData.postValue("")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorLiveData.postValue("${e.message}")
                }
            }
        }

    }

    fun clearErrorMessage() {
        errorLiveData.postValue(null)
    }
}
package com.example.brawlmobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.models.player.PlayerBrawlersUnlocked
import com.example.brawlmobile.models.player.PlayerInfoModel
import com.example.brawlmobile.remote.model.BrawlersUnlocked
import com.example.brawlmobile.repository.player.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerActivityViewModel(
    context: Context
) : ViewModel() {

    private val playerRepository: PlayerRepository
    private val TAG = "PlayerViewModel"

    init {
        playerRepository = PlayerRepository(context)

    }

    val playerInfo: MutableLiveData<PlayerInfoModel> by lazy {
        MutableLiveData<PlayerInfoModel>()
    }
    val playerBrawlersUnlocked: MutableLiveData<List<BrawlersUnlocked>> by lazy {
        MutableLiveData<List<BrawlersUnlocked>>()
    }
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getPlayerInfo(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "recupero i dati del player")
            try {
                val playerInfoFlow = playerRepository.fetchPlayerInfo(tag)
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
                    Log.d(TAG, "uiPlayerBrawlerUnlocked vale = $uiPlayerBrawlersUnlocked")

                    playerInfo.postValue(uiPlayerInfo)
                    playerBrawlersUnlocked.postValue(uiPlayerBrawlersUnlocked)
                }
            } catch (e: Exception) {
                errorLiveData.postValue("${e.message}")
            }
        }

    }

    fun clearErrorMessage() {
        errorLiveData.postValue(null)
    }
}
package com.example.brawlmobile.viewmodel.brawlStars

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.model.brawlStar.player.PlayerInfoModel
import com.example.brawlmobile.remote.brawlStars.model.BrawlersUnlocked
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
//                    Log.d(TAG, "uiPlayerBrawlerUnlocked vale = $uiPlayerBrawlersUnlocked")

                    withContext(Dispatchers.IO) {
                        Log.d(TAG,"sto facendo postvalue")
                        playerInfo.postValue(uiPlayerInfo)
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

    fun clearErrorMessage() {
        errorLiveData.postValue(null)
    }
}
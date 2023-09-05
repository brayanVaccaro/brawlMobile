package com.example.brawlmobile.repository.brawlStars.player

import com.example.brawlmobile.remote.brawlStars.model.PlayerInfoResponse
import com.example.brawlmobile.remote.clashRoyale.model.PlayerResponseModel
import kotlinx.coroutines.flow.Flow

interface PlayerRepositoryInterface {
    suspend fun fetchBrawlPlayerInfo(tag: String): Flow<PlayerInfoResponse>
    suspend fun fetchClashPlayerInfo(tag: String): Flow<PlayerResponseModel>
}
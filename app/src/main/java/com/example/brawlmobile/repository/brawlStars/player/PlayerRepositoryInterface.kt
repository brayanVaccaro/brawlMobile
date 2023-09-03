package com.example.brawlmobile.repository.brawlStars.player

import com.example.brawlmobile.remote.brawlStars.model.PlayerInfoResponse
import kotlinx.coroutines.flow.Flow

interface PlayerRepositoryInterface {
    suspend fun fetchPlayerInfo(tag: String): Flow<PlayerInfoResponse>
}
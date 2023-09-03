package com.example.brawlmobile.brawlStar.repository.player

import com.example.brawlmobile.brawlStar.remote.model.PlayerInfoResponse
import kotlinx.coroutines.flow.Flow

interface PlayerRepositoryInterface {
    suspend fun fetchPlayerInfo(tag: String): Flow<PlayerInfoResponse>
}
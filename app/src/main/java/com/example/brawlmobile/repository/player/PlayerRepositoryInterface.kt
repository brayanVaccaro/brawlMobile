package com.example.brawlmobile.repository.player

import com.example.brawlmobile.remote.model.PlayerInfoResponse
import kotlinx.coroutines.flow.Flow

interface PlayerRepositoryInterface {
    suspend fun fetchPlayerInfo(tag: String): Flow<PlayerInfoResponse>
}
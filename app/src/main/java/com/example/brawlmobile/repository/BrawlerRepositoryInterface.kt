package com.example.brawlmobile.repository

import com.example.brawlmobile.remote.model.BrawlerApiResponse
import kotlinx.coroutines.flow.Flow

interface BrawlerRepositoryInterface {
    suspend fun fetchBrawlersFlow(): Flow<BrawlerApiResponse>
}
package com.example.brawlmobile.repository.home

import com.example.brawlmobile.remote.model.BrawlerApiResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepositoryInterface {
    suspend fun fetchBrawlersFlow(): Flow<BrawlerApiResponse>
}
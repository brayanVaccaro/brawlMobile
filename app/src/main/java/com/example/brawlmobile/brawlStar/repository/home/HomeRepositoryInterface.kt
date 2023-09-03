package com.example.brawlmobile.brawlStar.repository.home

import com.example.brawlmobile.brawlStar.remote.model.BrawlerApiResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepositoryInterface {
    suspend fun fetchBrawlersFlow(): Flow<BrawlerApiResponse>
}
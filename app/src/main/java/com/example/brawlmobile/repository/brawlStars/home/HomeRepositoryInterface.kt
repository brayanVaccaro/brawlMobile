package com.example.brawlmobile.repository.brawlStars.home

import com.example.brawlmobile.remote.brawlStars.model.BrawlerApiResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepositoryInterface {
    suspend fun fetchBrawlersFlow(): Flow<BrawlerApiResponse>
}
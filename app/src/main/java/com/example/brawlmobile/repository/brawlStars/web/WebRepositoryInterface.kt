package com.example.brawlmobile.repository.brawlStars.web

import kotlinx.coroutines.flow.Flow

interface WebRepositoryInterface {
    suspend fun getTextFromWebFlow(name: String): Flow<List<String>>
    suspend fun getUrlsFromWebFlow(name: String): Flow<List<String>>
}
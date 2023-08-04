package com.example.brawlmobile.remote.brawler

import com.example.brawlmobile.remote.model.BrawlerApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("brawlers")
    suspend fun getAllBrawlers(): BrawlerApiResponse
}
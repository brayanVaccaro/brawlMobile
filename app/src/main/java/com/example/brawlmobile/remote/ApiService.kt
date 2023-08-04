package com.example.brawlmobile.remote

import com.example.brawlmobile.remote.model.BrawlerApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("brawlers")
    suspend fun getAllBrawlers(): BrawlerApiResponse
}
package com.example.brawlmobile.remote.brawlStars.brawler

import com.example.brawlmobile.remote.brawlStars.model.BrawlerApiResponse
import com.example.brawlmobile.remote.brawlStars.model.PlayerInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Lista dei brawler
    @GET("brawlers")
    suspend fun getAllBrawlers(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): BrawlerApiResponse

    // Info sul giocatore specifico
    @GET("players/{playerTag}")
    suspend fun getPlayerInfo(
        @Path("playerTag") tag: String
    ): PlayerInfoResponse
}
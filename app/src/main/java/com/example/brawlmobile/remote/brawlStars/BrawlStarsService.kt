package com.example.brawlmobile.remote.brawlStars

import com.example.brawlmobile.remote.brawlStars.model.BrawlerResponse
import com.example.brawlmobile.remote.brawlStars.model.BrawlPlayerResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BrawlStarsService {
    // Lista dei brawler
    @GET("brawlers")
    suspend fun getAllBrawlers(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): BrawlerResponse

    // Info sul giocatore specifico
    @GET("players/{playerTag}")
    suspend fun getPlayerInfo(
        @Path("playerTag") tag: String
    ): BrawlPlayerResponseModel
}
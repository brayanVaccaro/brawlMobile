package com.example.brawlmobile.remote.clashRoyale

import com.example.brawlmobile.remote.clashRoyale.model.CardResponse
import com.example.brawlmobile.remote.clashRoyale.model.PlayerResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ClashRoyaleService {

    @GET("cards")
    suspend fun getAllCards(): CardResponse

    // Info sul giocatore specifico
    @GET("players/{playerTag}")
    suspend fun getPlayerInfo(
        @Path("playerTag") tag: String
    ): PlayerResponseModel
}
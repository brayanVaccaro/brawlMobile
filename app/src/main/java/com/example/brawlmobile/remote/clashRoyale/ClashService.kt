package com.example.brawlmobile.remote.clashRoyale

import com.example.brawlmobile.remote.clashRoyale.model.CardResponse
import retrofit2.http.GET

interface ClashService {

    @GET("cards")
    suspend fun getAllCards(): CardResponse
}
package com.example.brawlmobile.repository.clashRoyale

import android.content.Context
import android.util.Log
import com.example.brawlmobile.remote.clashRoyale.RemoteApi
import com.example.brawlmobile.remote.clashRoyale.model.CardResponse

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepository(context: Context) {

    private val TAG = "CardRepository"
    private val remoteApi: RemoteApi = RemoteApi.create(context)

    suspend fun getAllCardFlow(): Flow<CardResponse> = flow {
        Log.d(TAG, "getAllCard()")

        val resultCard = remoteApi.clashService.getAllCards()

        val nameMap = mapOf(
            "p.e.k.k.a" to "pekka",
            "mini-p.e.k.k.a" to "mini-pekka"
        )

         resultCard.items.map { card ->
             val tranformedName = card.name.lowercase().replace(" ","-")

             card.transformedName = nameMap[tranformedName] ?: tranformedName
         }

        emit(resultCard)
        delay(1000)

    }
}
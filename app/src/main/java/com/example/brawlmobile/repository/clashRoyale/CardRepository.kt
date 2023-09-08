package com.example.brawlmobile.repository.clashRoyale

import android.content.Context
import com.example.brawlmobile.remote.clashRoyale.RetrofitClashRoyale
import com.example.brawlmobile.remote.clashRoyale.model.CardResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepository(context: Context) {

    private val TAG = "CardRepository"
    private val retrofitClashRoyale: RetrofitClashRoyale = RetrofitClashRoyale.create(context)

    suspend fun getAllCardFlow(): Flow<CardResponse> = flow {

        while (true) {
            val resultCard = retrofitClashRoyale.clashRoyaleService.getAllCards()

            val nameMap = mapOf(
                "p.e.k.k.a" to "pekka",
                "mini-p.e.k.k.a" to "mini-pekka"
            )

            resultCard.items.map { card ->
                val tranformedName = card.name.lowercase().replace(" ", "-")
                card.transformedName = nameMap[tranformedName] ?: tranformedName
            }
            // Emetto l'oggetto resultCard nel flow
            emit(resultCard)
            // Aggiorna il valore del flow ogni 5 secondi
            delay(5000)

        }
    }
}
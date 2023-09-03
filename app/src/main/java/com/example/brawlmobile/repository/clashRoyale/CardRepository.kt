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
        Log.d(TAG,"getAllCard()")
        val resultCard = remoteApi.clashService.getAllCards()

        emit(resultCard)
        delay(5000)
    }
}
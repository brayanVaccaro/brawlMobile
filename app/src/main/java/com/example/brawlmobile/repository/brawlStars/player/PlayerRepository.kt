package com.example.brawlmobile.repository.brawlStars.player

import android.content.Context
import android.util.Log
import com.example.brawlmobile.remote.brawlStars.brawler.RemoteApi
import com.example.brawlmobile.remote.brawlStars.model.PlayerInfoResponse
import com.example.brawlmobile.remote.clashRoyale.ClashRemoteApi
import com.example.brawlmobile.remote.clashRoyale.ClashService
import com.example.brawlmobile.remote.clashRoyale.model.PlayerResponseModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Il compito di questo repository è ottenere e modificare i dati dei Brawler da una API remota utilizzando RemoteApi
 */
class PlayerRepository(
    context: Context
) : PlayerRepositoryInterface {

    private val TAG = "PlayerRepository"

    private val brawlRemoteApi: RemoteApi = RemoteApi.create(context)
    private val clashRemoteApi: ClashRemoteApi = ClashRemoteApi.create(context)

    /**
     * Utilizza la funzione flow{...} per creare un flusso asincrono
     * dei dati ottenuti dalle API.
     * Continua ad emettere risultati ogni 5 secondi.
     */

    override suspend fun fetchBrawlPlayerInfo(tag: String): Flow<PlayerInfoResponse> = flow {
        Log.d(TAG, "Prendo le info del player, BrawlStars")
        while (true) {
//            try {
                val resultInfoPlayer = brawlRemoteApi.brawlerApiService.getPlayerInfo(tag)

                // Mappa per trasformare i nomi di alcuni brawler (necessario successivamente per ottenere le immagini giuste)
                val nameMap = mapOf(
                    "El primo" to "El-Primo",
                    "8-bit" to "8-Bit",
                    "Mr. p" to "Mr.P",
                    "R-t" to "R-T"
                )
                // Trasformazione dei nomi dei Brawler ottenuti, in base alla mappa di trasformazione
                resultInfoPlayer.brawlers.map { brawlerUnlocked ->
                    // Tutti i nomi dei brawler devono essere formattati
                    // Trasformo il nome rendendo la prima lettera maiuscola e le restanti minuscole
                    val transformedName =
                        brawlerUnlocked.name.lowercase().replaceFirstChar { it.uppercase() }
                    // Aggiorno il nome del brawler o con il nome trasformato dalla mappa, se presente, o lascia il nome trasformato
                    brawlerUnlocked.name = nameMap[transformedName] ?: transformedName
                }
                // Emetto l'oggetto resultInfoPlayer nel Flow
                emit(resultInfoPlayer)
                // Aggiorna il valore del Flow ogni 5 secondi
                delay(5000)


//            }
//            catch (e: Exception) {
//                Log.e(TAG,"Errore durante il recupero dei dati ${e.message}")
//
//            }
        }

    }

    override suspend fun fetchClashPlayerInfo(tag: String): Flow<PlayerResponseModel> = flow {
        Log.d(TAG,"Prendo le info del player, Clash Royale")
        while (true) {
            val resultInfoPlayer = clashRemoteApi.clashService.getPlayerInfo(tag)

            // Emetto l'oggetto resultInfoPlayer nel Flow
            emit(resultInfoPlayer)
            // Aggiorna il valore del Flow ogni 5 secondi
            delay(5000)
        }
    }
}
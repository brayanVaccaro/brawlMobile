package com.example.brawlmobile.repository.player

import android.content.Context
import android.util.Log
import com.example.brawlmobile.remote.brawler.RemoteApi
import com.example.brawlmobile.remote.model.PlayerInfoResponse
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

    private val remoteApi: RemoteApi = RemoteApi.create(context)

    /**
     * Utilizza la funzione flow{...} per creare un flusso asincrono
     * dei dati ottenuti dalla API.
     * Continua ad emettere risultati ogni 5 secondi.
     */

    override suspend fun fetchPlayerInfo(tag: String): Flow<PlayerInfoResponse> = flow {
        Log.d(TAG,"Prendo le info del player")
        while (true){
            val resultInfoPlayer = remoteApi.brawlerApiService.getPlayerInfo(tag)

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
                val transformedName = brawlerUnlocked.name.lowercase().replaceFirstChar { it.uppercase() }
                // Aggiorno il nome del brawler o con il nome trasformato dalla mappa, se presente, o lascia il nome trasformato
                brawlerUnlocked.name = nameMap[transformedName] ?: transformedName
            }
            // Emetto l'oggetto resultInfoPlayer nel Flow
            emit(resultInfoPlayer)
            // Aggiorna il valore del Flow ogni 5 secondi
            delay(5000)


            delay(5000)
        }

    }
}
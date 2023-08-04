package com.example.brawlmobile.repository

import android.content.Context
import android.util.Log
import com.example.brawlmobile.remote.brawler.RemoteApi
import com.example.brawlmobile.remote.model.BrawlerApiResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Il compito di questo repository è ottenere e modificare i dati dei Brawler da una API remota utilizzando RemoteApi
 */
class BrawlerRepository(
    context: Context
) : BrawlerRepositoryInterface {

    private val TAG = "BrawlerRepository"

    private val remoteApi: RemoteApi = RemoteApi.create(context)

    /**
     * Utilizza la funzione flow{...} per creare un flusso asincrono
     * dei dati ottenuti dalla API.
     * Continua ad emettere risultati ogni 5 secondi.
     */
    override suspend fun fetchBrawlersFlow(): Flow<BrawlerApiResponse> = flow {
        Log.d(TAG, "prendo i dati")
        while (true) {
            val resultBrawler = remoteApi.brawlerApiService.getAllBrawlers()
            // Mappa per trasformare i nomi di alcuni brawler (necessario successivamente per ottenere le immagini giuste)
            val nameMap = mapOf(
                "El primo" to "El-Primo",
                "8-bit" to "8-Bit",
                "Mr. p" to "Mr.P",
                "R-t" to "R-T"
            )
            // Trasformazione dei nomi dei Brawler ottenuti, in base alla mappa di trasformazione
            resultBrawler.items.map { brawler ->
                // Tutti i nomi dei brawler devono essere formattati
                // Trasformo il nome rendendo la prima lettera maiuscola e le restanti minuscole
                val transformedName = brawler.name.lowercase().replaceFirstChar { it.uppercase() }
                // Aggiorno il nome del brawler o con il nome trasformato dalla mappa, se presente, o lascia il nome trasformato
                brawler.name = nameMap[transformedName] ?: transformedName
            }
            // Emetto l'oggetto resultBrawler nel Flow
            emit(resultBrawler)
            // Aggiorna il valore del Flow ogni 5 secondi
            delay(5000)
        }
    }
}
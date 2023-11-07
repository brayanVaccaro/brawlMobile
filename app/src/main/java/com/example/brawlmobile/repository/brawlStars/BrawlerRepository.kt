package com.example.brawlmobile.repository.brawlStars

import android.content.Context
import com.example.brawlmobile.remote.brawlStars.RetrofitBrawlStars
import com.example.brawlmobile.remote.brawlStars.model.BrawlerResponse

/**
 * Il compito di questo repository è ottenere e modificare i dati dei Brawler da una API remota utilizzando RemoteApi
 */
class BrawlerRepository(
    context: Context
) {

    private val TAG = "BrawlerRepository"

    private val retrofitBrawlStars: RetrofitBrawlStars = RetrofitBrawlStars.create(context)

    private var afterCursor: String?
    private var limit = 36

    init {
        afterCursor = null
    }

    suspend fun fetchBrawlers(): BrawlerResponse {

        // Mappa per trasformare i nomi di alcuni brawler (necessario successivamente per ottenere le immagini giuste)
        val nameMap = mapOf(
            "El primo" to "El-Primo",
            "8-bit" to "8-Bit",
            "Mr. p" to "Mr.P",
            "R-t" to "R-T"
        )

        val resultBrawler = retrofitBrawlStars.brawlStarsService.getAllBrawlers(afterCursor, limit)

        // Trasformazione dei nomi dei Brawler ottenuti, in base alla mappa di trasformazione
        resultBrawler.items.map { brawler ->

            // Tutti i nomi dei brawler devono essere formattati, trasformo il nome rendendo la prima lettera maiuscola e le restanti minuscole
            val transformedName = brawler.name.lowercase().replaceFirstChar { it.uppercase() }
            // Aggiorno 'transformedName' con il nome trasformato dalla mappa, se presente, o lascio il nome trasformato
            brawler.transformedName = nameMap[transformedName] ?: transformedName
        }

        afterCursor = resultBrawler.paging?.cursors?.after

        return resultBrawler
//        if (afterCursor == null) {

//        } else {
//            resultBrawler
//        }

    }
}
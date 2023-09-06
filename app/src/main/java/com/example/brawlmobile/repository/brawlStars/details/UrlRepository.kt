package com.example.brawlmobile.repository.brawlStars.details

import android.util.Log
import com.example.brawlmobile.remote.brawlStars.web.Remote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class UrlRepository {

    private val TAG = "UrlRepository"

    suspend fun getUrlsFromWebFlow(name: String): Flow<List<String>> = flow {

        Log.d(TAG, "prendo il testo da web, name vale = $name")
        val response = Remote.webService.getTextFromWeb(name)
        if (response.isSuccessful) {
            val htmlBody = response.body()?.string() ?: ""

            val resultUrl = extractUrls(htmlBody)
//                Log.d(TAG,"Url da web vale = $resultUrl")

            emit(resultUrl)
        } else {
            Log.e(TAG, "Errore nella risposta dal sito Web: ${response.code()}")
        }

    }

    private fun extractUrls(html: String): List<String> {

        val imageUrls = mutableListOf<String>()

        // Utilizzo la libreria jsoup per fare parsing dell'HTMl
        val document: Document = Jsoup.parse(html)
        // Immagine nell'aside della pagina
        val imageProfile = document.select(".mw-parser-output > aside > figure > a > img")
        // Mi salvo solo l'url presente nell'attributo src
        val srcImageProfile = imageProfile.attr("src")
        // Aggiungo alla lista
        imageUrls.add(srcImageProfile)

        // Seleziono le restanti immagini del sito
        val images = document.select(".mw-parser-output > div[style*='float: right;'] > img")
        // Ciclo nei figli degli elementi img
        for (element in images) {
            // Mi salvo il testo dell'attributo data-src
            val src = element.attr("data-src")
            // Pulisco l'url e aggiungo alla lista
            imageUrls.add(src)
        }
//        Log.d(TAG,"imageUrls vale $imageUrls")

        return imageUrls
    }
}
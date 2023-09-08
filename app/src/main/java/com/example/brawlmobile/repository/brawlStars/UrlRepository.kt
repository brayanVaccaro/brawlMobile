package com.example.brawlmobile.repository.brawlStars

import com.example.brawlmobile.remote.brawlStars.RetrofitWeb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class UrlRepository {

    private val TAG = "UrlRepository"

    suspend fun getUrlsFromWebFlow(name: String): Flow<List<String>> = flow {

        val response = RetrofitWeb.webService.getTextFromWeb(name)
        val htmlBody = response.body()?.string() ?: ""
        val resultUrl = extractUrls(htmlBody)
        emit(resultUrl)
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
            // Aggiungo alla lista
            imageUrls.add(src)
        }

        return imageUrls
    }
}
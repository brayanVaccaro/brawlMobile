package com.example.brawlmobile.repository.brawlStars.web

import android.util.Log
import com.example.brawlmobile.remote.brawlStars.web.Remote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class WebRepository : WebRepositoryInterface {

    private val TAG = "WebRepository"

    override suspend fun getTextFromWebFlow(name: String): Flow<List<String>> = flow {

//            Log.d(TAG,"prendo il testo da web, name vale $name")
            val response = Remote.webService.getTextFromWeb(name)
            if (response.isSuccessful) {
                val htmlBody = response.body()?.string() ?: ""
                val resultText = extractText(htmlBody)
//                Log.d(TAG,"il testo da web vale = $resultText")

                emit(resultText)
            } else {
                Log.d(TAG,"Errore nella risposta dal sito Web: ${response.code()}")
            }

    }

    override suspend fun getUrlsFromWebFlow(name: String): Flow<List<String>> = flow {

            Log.d(TAG,"prendo il testo da web, name vale = $name")
            val response = Remote.webService.getTextFromWeb(name)
            if (response.isSuccessful) {
                val htmlBody = response.body()?.string() ?: ""

                val resultUrl = extractUrls(htmlBody)
//                Log.d(TAG,"Url da web vale = $resultUrl")

                emit(resultUrl)
            } else {
                Log.d(TAG,"Errore nella risposta dal sito Web: ${response.code()}")
            }

    }

    private fun extractText(html: String): List<String> {
        // Utilizzo la libreria jsoup per fare parsing dell'HTMl
        val document: Document = Jsoup.parse(html)

        // Seleziono tutti gli elementi div con classe "quote-block"
        val quoteBlockElements = document.select("div.quote-block")

        // Lista per memorizzare il testo estratto dai quote-block
        val quoteBlockText = mutableListOf<String>()

        // Ciclo attraverso gli elementi quote-block
        for (element in quoteBlockElements) {
            val stringBuilder = StringBuilder()
            // Ciclo attraverso i figli di ogni elemento quote-block
            for(child in element.children()) {
                // Seleziono gli elementi i e b e li aggiungo allo stringBuilder
                if (child.tagName() == "i" || child.tagName() == "b") {
                    val textTrimmed = child.text().trim{it == '"'}
                    stringBuilder.append(textTrimmed)
                }
            }
        quoteBlockText.add(stringBuilder.toString().trim())

        }

//        Log.d(TAG,"testo dagli elementi quote-block vale = $quoteBlockText")
        return quoteBlockText

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

    private fun cleanUrls(url: String): String {

        return url.substringBeforeLast(".png") + ".png"

    }
}
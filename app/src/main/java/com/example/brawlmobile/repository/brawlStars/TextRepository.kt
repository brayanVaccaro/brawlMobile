package com.example.brawlmobile.repository.brawlStars

import com.example.brawlmobile.remote.brawlStars.RetrofitWeb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class TextRepository {

    private val TAG = "TextRepository"

    suspend fun getTextFromWebFlow(name: String): Flow<List<String>> = flow {

        val response = RetrofitWeb.webService.getTextFromWeb(name)
        val htmlBody = response.body()?.string() ?: ""
        val resultText = extractText(htmlBody)
        emit(resultText)
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
            for (child in element.children()) {
                // Seleziono gli elementi i e b e li aggiungo allo stringBuilder
                if (child.tagName() == "i" || child.tagName() == "b") {
                    val textTrimmed = child.text().trim { it == '"' }
                    stringBuilder.append(textTrimmed)
                }
            }
            quoteBlockText.add(stringBuilder.toString().trim())
        }
//        Log.d(TAG,"quoteBlockText vale $quoteBlockText")
//        Log.d(TAG,"quoteBlockText size vale ${quoteBlockText.size}")
        return quoteBlockText
    }
}
package com.example.brawlmobile.repository.web

import android.util.Log
import com.example.brawlmobile.remote.web.Remote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class WebRepository : WebRepositoryInterface {

    private val TAG = "WebRepository"

    override suspend fun getTextFromWebFlow(name: String): Flow<List<String>> = flow {
        while (true) {
            Log.d(TAG,"prendo il testo da web")
            val response = Remote.webService.getTextFromWeb(name)
            if (response.isSuccessful) {
                val htmlBody = response.body()?.string() ?: ""
                val resultText = extractText(htmlBody)
                Log.d(TAG,"il testo da web vale = $resultText")

                emit(resultText)
            } else {
                Log.d(TAG,"Errore nella risposta dal sito Web: ${response.code()}")
            }
        }
    }

    private fun extractText(html: String): List<String> {
        val document: Document = Jsoup.parse(html)
        val quoteBlockElements = document.select("div.quote-block")

        val quoteBlockText = mutableListOf<String>()

        for (element in quoteBlockElements) {
            val stringBuilder = StringBuilder()

            for(child in element.children()) {
                if (child.tagName() == "i" || child.tagName() == "b") {
                    stringBuilder.append(child.text()).append(" ")
                }
            }
        quoteBlockText.add(stringBuilder.toString().trim())

        }

        Log.d(TAG,"testo dagli elementi quote-block vale = $quoteBlockText")
        return quoteBlockText

    }
}
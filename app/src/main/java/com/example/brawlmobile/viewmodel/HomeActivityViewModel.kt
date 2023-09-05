package com.example.brawlmobile.viewmodel.brawlStars

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.brawlmobile.R
import com.example.brawlmobile.model.brawlStar.brawler.BrawlerModel
import com.example.brawlmobile.model.brawlStar.web.ImagesModel
import com.example.brawlmobile.model.brawlStar.web.TextModel
import com.example.brawlmobile.model.clashRoyale.CardModel
import com.example.brawlmobile.repository.brawlStars.home.HomeRepository
import com.example.brawlmobile.repository.brawlStars.details.UrlRepository
import com.example.brawlmobile.repository.brawlStars.details.TextRepository
import com.example.brawlmobile.repository.clashRoyale.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivityViewModel(context: Context) : ViewModel() {

    // Repository per accedere ai dati dei Brawler da una API remota
    private val brawlerRepository: HomeRepository
    private val cardRepository: CardRepository



    // TAG per il logging
    private val TAG = "MainActivityViewModel"

    // Urls da cui prendere le immagini dei brawler, i nomi dei brawler saranno gli endpoint
    private val brawlerImagesUrl = "https://cdn-old.brawlify.com/brawler-bs/"
    private val clashImageUrl = "https://cdn.royaleapi.com/static/img/cards-150/"

    // Lista per salvare gli url per cui fare il preload
    private var imgUrlsToPreload: MutableList<String> = mutableListOf()

    // Inizializzazione dei repository nel costruttore
    init {
        brawlerRepository = HomeRepository(context)
        cardRepository = CardRepository(context)
    }

    // LiveData per mantenere l'elenco dei Brawler aggiornato nell'UI
    val brawlers: MutableLiveData<List<BrawlerModel>> by lazy {
        MutableLiveData<List<BrawlerModel>>()
    }


    val cards: MutableLiveData<List<CardModel>> by lazy {
        MutableLiveData<List<CardModel>>()
    }

    val errorLiveData: MutableLiveData<String> = MutableLiveData()


    // Metodo per ottenere i Brawler dall'API remota
    fun getBrawlers() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "recupero il primo flow dal BrawlerRepo")
            imgUrlsToPreload.clear()
            Log.d(TAG, "PRIMA imgUrlsToPreload size vale ${imgUrlsToPreload.size}")

            try {
                // Ottengo un FLow di BrawlerApiResponse da BrawlerRepository
                val brawlerFlow = brawlerRepository.fetchBrawlersFlow()

                // Raccolgo i dati ottenuti dal flusso
                brawlerFlow.collect { brawlersFromRepo ->
                    // Trasformo i dati dell'API remota nel modello BrawlerModel dell'UI
                    val uiBrawlers = brawlersFromRepo.items.map {
                        BrawlerModel(
                            id = it.id,
                            name = it.name,
                            starPowers = it.starPowers,
                            gadgets = it.gadgets,
                            spriteUrl = "${brawlerImagesUrl}${it.name}.png"
                        )
                    }
                    Log.d(TAG, "sto facendo il postvalue")
                    // Aggiorno il LiveData dei Brawler con i dati ottenuti
                    brawlers.postValue(uiBrawlers)
                    // Aggiungo alla lista degli url da precaricare
//                    imgUrlsToPreload.addAll(uiBrawlers.mapNotNull { it.spriteUrl })
//                    Log.d(TAG, "DOPO imgUrlsToPreload size vale ${imgUrlsToPreload.size}")
//
//                    // Preload delle immagini
//                    preloadImages(context, imgUrlsToPreload)

                }
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }

    fun getCards() {
        viewModelScope.launch(Dispatchers.IO) {

            try {

                val cardFlow = cardRepository.getAllCardFlow()
                cardFlow.collect { cardFromRepo ->
                    val uiCard = cardFromRepo.items.map {
                        CardModel(
                            name = it.name,
                            transformedName = it.transformedName ?: "",
                            maxLevel = it.maxLevel,
                            maxEvolutionLevel = it.maxEvolutionLevel,
//                            urlNormal = it.iconUrls.medium,
                            urlNormal = "${clashImageUrl}${it.transformedName}.png",
                            urlEvolution = it.iconUrls.evolutionMedium ?: ""
                        )
                    }
                    cards.postValue(uiCard)
                }
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }

        }
    }

    // Metodo per fare il preload di tutte le immagini
    private fun preloadImages(context: Context, urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            //
            Log.d(TAG, "sto facendo il preload")

            for (imageUrl in urls) {
//                Log.d(TAG, "imageUrl vale $imageUrl")
                Glide.with(context)
                    .load(imageUrl)
                    .preload()
            }
            Log.d(TAG, "ho finito il preload")
        }
    }



    fun clearErrorMessage() {
        errorLiveData.postValue(null)
    }
}
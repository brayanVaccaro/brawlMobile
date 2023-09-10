package com.example.brawlmobile.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.model.brawlStar.brawler.BrawlerModel
import com.example.brawlmobile.model.clashRoyale.CardModel
import com.example.brawlmobile.repository.brawlStars.BrawlerRepository
import com.example.brawlmobile.repository.clashRoyale.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivityViewModel(context: Context) : ViewModel() {

    // Repository per accedere ai dati dei Brawler da una API remota
    private val brawlerRepository: BrawlerRepository
    private val cardRepository: CardRepository

    // TAG per il logging
    private val TAG = "MainActivityViewModel"

    // Urls da cui prendere le immagini dei brawler, i nomi dei brawler saranno gli endpoint
    private val brawlerImagesUrl = "https://cdn-old.brawlify.com/brawler-bs/"
    private val clashImageUrl = "https://cdn.royaleapi.com/static/img/cards-150/"

    private var maxSize = 72
    private var totalLoaded = 0


    // Inizializzazione dei repository nel costruttore
    init {
        brawlerRepository = BrawlerRepository(context)
        cardRepository = CardRepository(context)

    }

    // LiveData per mantenere l'elenco dei Brawler
    private val _brawlers = MutableLiveData<List<BrawlerModel>>()

    // LiveData per esporre l'elenco dei Brawler alla activity
    val brawlers: LiveData<List<BrawlerModel>> = _brawlers

    // LiveData per mantenere l'elenco delle carte
    val cards: MutableLiveData<List<CardModel>> by lazy {
        MutableLiveData<List<CardModel>>()
    }

    // LiveData per gli eventuali errori
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    // Metodo per ottenere i Brawler dall'API remota
    fun getBrawlers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                do {
                    val brawlerResponse = brawlerRepository.fetchBrawlers()
                    val uiBrawlers = brawlerResponse.items.map {
                        BrawlerModel(
                            id = it.id,
                            name = it.name,
                            transformedName = it.transformedName ?: "",
                            firstStarPower = it.starPowers[0].name,
                            secondStarPower = it.starPowers[1].name,
                            firstGadget = it.gadgets[0].name,
                            secondGadget = it.gadgets[1].name,
                            spriteUrl = "${brawlerImagesUrl}${it.transformedName}.png"
                        )
                    }
                    totalLoaded += uiBrawlers.size
                    _brawlers.postValue(uiBrawlers)
                } while (totalLoaded < maxSize)
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
                            id = it.id,
                            name = it.name,
                            transformedName = it.transformedName ?: "",
                            maxLevel = it.maxLevel,
                            maxEvolutionLevel = it.maxEvolutionLevel,
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

    fun clearErrorMessage() {
        errorLiveData.postValue(null)
    }
}
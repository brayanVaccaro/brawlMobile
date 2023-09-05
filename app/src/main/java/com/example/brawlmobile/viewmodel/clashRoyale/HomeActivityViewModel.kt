package com.example.brawlmobile.viewmodel.clashRoyale

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.model.clashRoyale.CardModel
import com.example.brawlmobile.repository.clashRoyale.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivityViewModel(context: Context): ViewModel() {

    private val cardRepository: CardRepository

    private val TAG: String

    private val imageUrl = "https://cdn.royaleapi.com/static/img/cards-150/"

    init {
        cardRepository = CardRepository(context)
        TAG = "HomeActivityViewModel"
    }

    val cards: MutableLiveData<List<CardModel>> by lazy {
        MutableLiveData<List<CardModel>>()
    }

    fun getCards() {
    viewModelScope.launch(Dispatchers.IO) {

                val cardFlow = cardRepository.getAllCardFlow()
                cardFlow.collect {cardFromRepo ->
                    val uiCard = cardFromRepo.items.map {
                        CardModel(
                            name = it.name,
                            transformedName = it.transformedName ?: "",
                            maxLevel = it.maxLevel,
                            maxEvolutionLevel = it.maxEvolutionLevel,
//                            urlNormal = it.iconUrls.medium,
                            urlNormal = "${imageUrl}${it.transformedName}.png",
                            urlEvolution = it.iconUrls.evolutionMedium ?: ""
                        )
                    }
                    cards.postValue(uiCard)
                }

        }
    }
}
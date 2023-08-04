package com.example.brawlmobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.models.BrawlerModel
import com.example.brawlmobile.repository.BrawlerRepository
import com.example.brawlmobile.repository.BrawlerRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(context: Context): ViewModel() {

    // Repository per accedere ai dati dei Brawler da una API remota
    private val brawlerRepository: BrawlerRepositoryInterface

    // TAG per il logging
    private val TAG ="MainActivityViewModel"

    // Inizializzazione del repository nel costruttore
    init {
        brawlerRepository = BrawlerRepository(context)
    }

    // LiveData per mantenere l'elenco dei Brawler aggiornato nell'UI
    val brawlers: MutableLiveData<List<BrawlerModel>> by lazy {
        MutableLiveData<List<BrawlerModel>>()
    }

    // Metodo per ottenere i Brawler dall'API remota
    fun getBrawlers() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG,"recupero il flow dal repo")

            // Ottengo un FLow di BrawlerApiResponse da BrawlerRepository
            val brawlerFlow = brawlerRepository.fetchBrawlersFlow()

            // Raccolgo i dati ottenuti dal flusso
            brawlerFlow.collect{brawlersFromRepo ->
                // Trasformo i dati dell'API remota nel modello BrawlerModel dell'UI
                val uiBrawlers = brawlersFromRepo.items.map {
                    BrawlerModel(
                        id = it.id,
                        name =  it.name,
                        starPowers = it.starPowers,
                        gadgets = it.gadgets
                    )
                }

                // Aggiorno il LiveData dei Brawler con i dati ottenuti
                brawlers.postValue(uiBrawlers)
            }
        }
    }

}
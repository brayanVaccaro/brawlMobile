package com.example.brawlmobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.R
import com.example.brawlmobile.models.brawler.BrawlerModel
import com.example.brawlmobile.models.web.TextModel
import com.example.brawlmobile.repository.BrawlerRepository
import com.example.brawlmobile.repository.BrawlerRepositoryInterface
import com.example.brawlmobile.repository.web.WebRepository
import com.example.brawlmobile.repository.web.WebRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivityViewModel(context: Context) : ViewModel() {

    // Repository per accedere ai dati dei Brawler da una API remota
    private val brawlerRepository: BrawlerRepositoryInterface
    private val webRepository: WebRepositoryInterface

    // TAG per il logging
    private val TAG = "MainActivityViewModel"
    private val spriteUrl = "https://cdn-old.brawlify.com/brawler-bs/"

    // Inizializzazione del repository nel costruttore
    init {
        brawlerRepository = BrawlerRepository(context)
        webRepository = WebRepository()
    }

    // LiveData per mantenere l'elenco dei Brawler aggiornato nell'UI
    val brawlers: MutableLiveData<List<BrawlerModel>> by lazy {
        MutableLiveData<List<BrawlerModel>>()
    }

    val webText: MutableLiveData<TextModel> by lazy {
        MutableLiveData<TextModel>()
    }

    // Metodo per ottenere i Brawler dall'API remota
    fun getBrawlers() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "recupero il primo flow dal BrawlerRepo")

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
                        spriteUrl = "${spriteUrl}${it.name}.png"
                    )
                }

                // Aggiorno il LiveData dei Brawler con i dati ottenuti
                brawlers.postValue(uiBrawlers)
            }
        }
    }

    fun getWebText(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "recupero il secondo flow dal WebRepo")

            val textFlow = webRepository.getTextFromWebFlow(name)
            Log.d(TAG, "textFlow vale = $textFlow")
            textFlow.collect {

                var uiText: TextModel? = null
                when (it.size) {
                    7 -> {
                        Log.d(TAG, "sono in size 7")

                        uiText = TextModel(
                            description = it[0],
                            trait = "",
                            firstAttack = it[1],
                            secondAttack = "",
                            firstSuper = it[2],
                            secondSuper = "",
                            firstGadget = it[3],
                            secondGadget = it[4],
                            firstStarPower = it[5],
                            secondStarPower = it[6],
                            layoutResId = R.layout.item_text_size7
                        )
                        Log.d(TAG, "uiText_size7 vale $uiText")

                    }
                    8 -> {
                        Log.d(TAG, "sono in size 8")
                        uiText = TextModel(
                            description = it[0],
                            trait = it[1],
                            firstAttack = it[2],
                            secondAttack = "",
                            firstSuper = it[3],
                            secondSuper = "",
                            firstGadget = it[4],
                            secondGadget = it[5],
                            firstStarPower = it[6],
                            secondStarPower = it[7],
                            layoutResId = R.layout.item_text_size8
                        )
                        Log.d(TAG, "uiText_size8 vale $uiText")
                    }
                    9 -> {
                        Log.d(TAG, "sono in size 8")
                        uiText = TextModel(
                            description = it[0],
                            trait = "",
                            firstAttack = it[1],
                            secondAttack = it[2],
                            firstSuper = it[3],
                            secondSuper = it[4],
                            firstGadget = it[5],
                            secondGadget = it[6],
                            firstStarPower = it[7],
                            secondStarPower = it[8],
                            layoutResId = R.layout.item_text_size9
                        )
                        Log.d(TAG, "uiText_size8 vale $uiText")
                    }

                }

                webText.postValue(uiText)
            }
        }
    }


}
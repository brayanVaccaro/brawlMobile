package com.example.brawlmobile.brawlStar.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.brawlmobile.R
import com.example.brawlmobile.brawlStar.models.brawler.BrawlerModel
import com.example.brawlmobile.brawlStar.models.web.ImagesModel
import com.example.brawlmobile.brawlStar.models.web.TextModel
import com.example.brawlmobile.brawlStar.repository.home.HomeRepository
import com.example.brawlmobile.brawlStar.repository.home.HomeRepositoryInterface
import com.example.brawlmobile.brawlStar.repository.web.WebRepository
import com.example.brawlmobile.brawlStar.repository.web.WebRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivityViewModel(context: Context) : ViewModel() {

    // Repository per accedere ai dati dei Brawler da una API remota
    private val brawlerRepository: HomeRepositoryInterface

    // Repository per prelevare dati da web
    private val webRepository: WebRepositoryInterface


    // TAG per il logging
    private val TAG = "MainActivityViewModel"

    // Url da cui prendere le immagini dei brawler, i nomi dei brawler saranno gli endpoint
    private val spriteUrl = "https://cdn-old.brawlify.com/brawler-bs/"

    // Lista per salvare gli url per cui fare il preload
    private var imgUrlsToPreload: MutableList<String> = mutableListOf()

    // Inizializzazione dei repository nel costruttore
    init {
        brawlerRepository = HomeRepository(context)
        webRepository = WebRepository()

    }

    // LiveData per mantenere l'elenco dei Brawler aggiornato nell'UI
    val brawlers: MutableLiveData<List<BrawlerModel>> by lazy {
        MutableLiveData<List<BrawlerModel>>()
    }

    // LiveData per mantenere l'elenco dei testi aggiornato nell'UI
    val webText: MutableLiveData<TextModel> by lazy {
        MutableLiveData<TextModel>()
    }

    // LiveData per mantenere l'elenco degli url delle immagini aggiornato nell'UI
    val webUrls: MutableLiveData<ImagesModel> by lazy {
        MutableLiveData<ImagesModel>()
    }
    val errorLiveData: MutableLiveData<String> = MutableLiveData()


    // Metodo per ottenere i Brawler dall'API remota
    fun getBrawlers(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "recupero il primo flow dal BrawlerRepo")
            imgUrlsToPreload = mutableListOf()
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
                            spriteUrl = "${spriteUrl}${it.name}.png"
                        )
                    }
                    // Aggiungo alla lista degli url da precaricare
                    imgUrlsToPreload.addAll(uiBrawlers.mapNotNull { it.spriteUrl })
                    Log.d(TAG, "DOPO imgUrlsToPreload size vale ${imgUrlsToPreload.size}")
//                    preloadImages(context,imgUrlsToPreload)
                    // Preload delle immagini
                    preloadImages(context, imgUrlsToPreload)
//                    imgUrlsToPreload.removeAll(imgUrlsToPreload)
//                    uiBrawlers.mapNotNull { it.spriteUrl }.let { imgUrlsToPreload.addAll(it) }
                    // Aggiorno il LiveData dei Brawler con i dati ottenuti
                    Log.d(TAG, "sto facendo il postvalue")
                    brawlers.postValue(uiBrawlers)
                }





            } catch (e: Exception) {
                errorLiveData.postValue("${e.message}")
            }
        }
    }

    // Metodo per fare il preload di tutte le immagini
    private fun preloadImages(context: Context, urls: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
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

    // Metodo per ottenere i testi dal sito web
    fun getWebText(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "recupero il secondo flow dal WebRepo")

            val textFlow = webRepository.getTextFromWebFlow(name)
//            Log.d(TAG, "textFlow vale = $textFlow")
            textFlow.collect {

                var uiText: TextModel? = null
                // In base alla size del Flow ho diversi TextModel da dover rappresentare
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
//                        Log.d(TAG, "uiText_size7 vale $uiText")

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
//                        Log.d(TAG, "uiText_size8 vale $uiText")
                    }
                    9 -> {
                        Log.d(TAG, "sono in size 9")
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
//                        Log.d(TAG, "uiText_size9 vale $uiText")
                    }

                }

                webText.postValue(uiText)
            }
        }
    }

    // Metodo per ottenere gli urls delle immagini
    fun getWebUrls(name: String) {
        viewModelScope.launch {
            val urlsFlow = webRepository.getUrlsFromWebFlow(name)
            var uiUrls: ImagesModel? = null
            urlsFlow.collect {
                Log.d(TAG, "size della Lista degli urls vale = ${it.size}")
                // In base alla size del flow imposto le immagini corrette da dover visualizzare
                when (it.size) {
                    5 -> {
                        uiUrls = ImagesModel(
                            defaultSkin = it[0],
                            firstGadgetUrl = it[1],
                            secondGadgetUrl = it[2],
                            firstStarPowerUrl = it[3],
                            secondStarPowerUrl = it[4]
                        )
                    }
                    7 -> {
                        uiUrls = ImagesModel(
                            defaultSkin = it[0],
                            firstGadgetUrl = it[3],
                            secondGadgetUrl = it[4],
                            firstStarPowerUrl = it[5],
                            secondStarPowerUrl = it[6]
                        )
                    }
                    8 -> {
                        uiUrls = ImagesModel(
                            defaultSkin = it[0],
                            firstGadgetUrl = it[3],
                            secondGadgetUrl = it[4],
                            firstStarPowerUrl = it[6],
                            secondStarPowerUrl = it[7]
                        )
                    }
                }
                webUrls.postValue(uiUrls)
            }

        }
    }

    fun clearErrorMessage() {
        errorLiveData.postValue(null)
    }
}
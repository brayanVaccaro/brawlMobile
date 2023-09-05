package com.example.brawlmobile.viewmodel.brawlStars

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.R
import com.example.brawlmobile.model.brawlStar.web.ImagesModel
import com.example.brawlmobile.model.brawlStar.web.TextModel
import com.example.brawlmobile.repository.brawlStars.details.TextRepository
import com.example.brawlmobile.repository.brawlStars.details.UrlRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsActivityViewModel(context: Context): ViewModel() {

    // Repository per prelevare dati da web
    private val textRepository: TextRepository

    private val urlRepository: UrlRepository

    private val TAG = "DetailsActivityViewModel"
    init {
        textRepository = TextRepository()
        urlRepository = UrlRepository()
    }
    // LiveData per mantenere l'elenco degli url delle immagini aggiornato nell'UI
    val webUrls: MutableLiveData<ImagesModel> by lazy {
        MutableLiveData<ImagesModel>()
    }
    // LiveData per mantenere l'elenco dei testi aggiornato nell'UI
    val webText: MutableLiveData<TextModel> by lazy {
        MutableLiveData<TextModel>()
    }

    // Metodo per ottenere i testi dal sito web
    fun getWebText(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "recupero il secondo flow dal WebRepo")

            val textFlow = textRepository.getTextFromWebFlow(name)
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
            val urlsFlow = urlRepository.getUrlsFromWebFlow(name)
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


}
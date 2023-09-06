package com.example.brawlmobile.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.data.dao.BrawlerDao
import com.example.brawlmobile.data.dao.CardDao
import com.example.brawlmobile.data.database.AppDatabase
import com.example.brawlmobile.data.entities.BrawlerEntity
import com.example.brawlmobile.data.entities.CardEntity
import com.example.brawlmobile.repository.brawlStars.favourite.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteActivityViewModel(
    context: Context
) : ViewModel() {

    private val favouriteRepository: FavouriteRepository
    private val brawlerDao: BrawlerDao
    private val cardDao: CardDao

    init {

        brawlerDao = AppDatabase.getInstance(context).favouriteBrawlerDao()
        cardDao = AppDatabase.getInstance(context).favouriteCardDao()
        favouriteRepository = FavouriteRepository(brawlerDao, cardDao)
    }


    val allFavouriteBrawlers: LiveData<List<BrawlerEntity>> = favouriteRepository.getAllBrawlers()

    val allFavouriteCards: LiveData<List<CardEntity>> = favouriteRepository.getAllCards()

    // Metodo per inserire il brawler al click del cuore
    fun insertFavouriteBrawler(favouriteBrawler: BrawlerEntity) {
        viewModelScope.launch {
            favouriteRepository.insertBrawler(favouriteBrawler)
        }
    }

    fun deleteBrawler(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteRepository.deleteBrawlerByName(name)
        }
    }

    fun insertFavouriteCard(favouriteCard: CardEntity) {
        viewModelScope.launch {
            favouriteRepository.insertCard(favouriteCard)
        }
    }

    fun deleteCard(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteRepository.deleteCardByName(name)
        }
    }
}
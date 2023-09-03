package com.example.brawlmobile.brawlStar.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlmobile.brawlStar.data.dao.FavouriteBrawlerDao
import com.example.brawlmobile.brawlStar.data.database.AppDatabase
import com.example.brawlmobile.brawlStar.data.entities.FavouriteBrawlerEntity
import com.example.brawlmobile.brawlStar.repository.favourite.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteActivityViewModel(
    context: Context
) : ViewModel() {

    private val favouriteRepository: FavouriteRepository
    private val favouriteBrawlerDao: FavouriteBrawlerDao

    init {

        favouriteBrawlerDao = AppDatabase.getInstance(context).favouriteBrawlerDao()
        favouriteRepository = FavouriteRepository(favouriteBrawlerDao)
    }


    val allFavouriteBrawlers: LiveData<List<FavouriteBrawlerEntity>> = favouriteRepository.getAllBrawlers()

    // Metodo per inserire il brawler al click del cuore
    fun insertFavouriteBrawler(favouriteBrawler: FavouriteBrawlerEntity) {
        viewModelScope.launch {
            favouriteRepository.insertBrawlers(favouriteBrawler)
        }
    }

    fun deleteBrawler(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteRepository.deleteByName(name)
        }
    }
}
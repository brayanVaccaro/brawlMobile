package com.example.brawlmobile.brawlStar.repository.favourite

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.brawlmobile.brawlStar.data.dao.FavouriteBrawlerDao
import com.example.brawlmobile.brawlStar.data.entities.FavouriteBrawlerEntity

class FavouriteRepository(private val favouriteBrawlerDao: FavouriteBrawlerDao) {
    fun getAllBrawlers(): LiveData<List<FavouriteBrawlerEntity>> {
        return favouriteBrawlerDao.getAllFavouriteBrawlers()
    }
    fun deleteByName(name: String) {
        return favouriteBrawlerDao.deleteByName(name)
    }

    suspend fun insertBrawlers(brawler: FavouriteBrawlerEntity) {
        favouriteBrawlerDao.insertFavouriteBrawler(brawler)
        Log.d("FavRepo","ho inserito ai preferiti")

    }
}
package com.example.brawlmobile.repository.favourite

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.brawlmobile.data.dao.FavouriteBrawlerDao
import com.example.brawlmobile.data.entities.FavouriteBrawlerEntity

class FavouriteRepository(private val favouriteBrawlerDao: FavouriteBrawlerDao) {
    fun deleteBrawler(): LiveData<List<FavouriteBrawlerEntity>> {
        return favouriteBrawlerDao.deleteFavouriteBrawler()
    }

    suspend fun insertBrawlers(brawler: FavouriteBrawlerEntity) {
        favouriteBrawlerDao.insertFavouriteBrawler(brawler)
        Log.d("FavRepo","ho inserito ai preferiti")

    }
}
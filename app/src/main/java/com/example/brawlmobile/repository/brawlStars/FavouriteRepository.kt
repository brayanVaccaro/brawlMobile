package com.example.brawlmobile.repository.brawlStars

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.brawlmobile.data.dao.BrawlerDao
import com.example.brawlmobile.data.dao.CardDao
import com.example.brawlmobile.data.entities.BrawlerEntity
import com.example.brawlmobile.data.entities.CardEntity

class FavouriteRepository(private val brawlerDao: BrawlerDao, private val cardDao: CardDao) {
    fun getAllBrawlers(): LiveData<List<BrawlerEntity>> {
        return brawlerDao.getAllBrawlers()
    }
    fun deleteBrawlerByName(name: String) {
        return brawlerDao.deleteByName(name)
    }

    suspend fun insertBrawler(brawler: BrawlerEntity) {
        brawlerDao.insertBrawler(brawler)
        Log.d("FavRepo","ho inserito ai preferiti")
    }

    fun getAllCards(): LiveData<List<CardEntity>> {
        return cardDao.getAllCards()
    }
    fun deleteCardByName(name: String) {
        return cardDao.deleteByName(name)
    }

    suspend fun insertCard(card: CardEntity) {
        cardDao.insertCard(card)
    }
}
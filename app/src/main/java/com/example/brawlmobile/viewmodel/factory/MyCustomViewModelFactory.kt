package com.example.brawlmobile.viewmodel.factory

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brawlmobile.activity.brawlStars.BrawlDetailsActivity
import com.example.brawlmobile.activity.brawlStars.BrawlFavouriteActivity
import com.example.brawlmobile.activity.brawlStars.BrawlHomeActivity
import com.example.brawlmobile.activity.brawlStars.BrawlPlayerActivity
import com.example.brawlmobile.activity.clashRoyale.ClashFavouriteActivity
import com.example.brawlmobile.activity.clashRoyale.ClashHomeActivity
import com.example.brawlmobile.activity.clashRoyale.ClashPlayerActivity
import com.example.brawlmobile.viewmodel.FavouriteActivityViewModel
import com.example.brawlmobile.viewmodel.HomeActivityViewModel
import com.example.brawlmobile.viewmodel.PlayerActivityViewModel
import com.example.brawlmobile.viewmodel.brawlStars.DetailsActivityViewModel

class MyCustomViewModelFactory(private val context: Context, private val activityType: Class<out AppCompatActivity>) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (activityType) {
            BrawlHomeActivity::class.java, ClashHomeActivity::class.java-> {
                if (modelClass.isAssignableFrom(HomeActivityViewModel::class.java)) {
                    HomeActivityViewModel(context) as T
                } else if (modelClass.isAssignableFrom(FavouriteActivityViewModel::class.java)) {
                    FavouriteActivityViewModel(context) as T
                } else{
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }

            BrawlPlayerActivity::class.java, ClashPlayerActivity::class.java -> {
                if (modelClass.isAssignableFrom(PlayerActivityViewModel::class.java)) {
                    PlayerActivityViewModel(context) as T
                } else {
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }

            BrawlFavouriteActivity::class.java, ClashFavouriteActivity::class.java  -> {
                if (modelClass.isAssignableFrom(FavouriteActivityViewModel::class.java)) {
                    FavouriteActivityViewModel(context) as T
                } else {
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }

            BrawlDetailsActivity::class.java -> {
                if (modelClass.isAssignableFrom(DetailsActivityViewModel::class.java)) {
                    DetailsActivityViewModel() as T
                } else {
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }

            else -> throw IllegalArgumentException("Unknown Activity type")
        }
    }
}
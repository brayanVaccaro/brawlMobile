package com.example.brawlmobile.viewmodel.brawlStars.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brawlmobile.viewmodel.brawlStars.PlayerActivityViewModel

class PlayerActivityViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerActivityViewModel::class.java)) {
            return PlayerActivityViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
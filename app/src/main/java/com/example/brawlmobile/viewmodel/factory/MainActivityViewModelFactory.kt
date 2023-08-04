package com.example.brawlmobile.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brawlmobile.viewmodel.MainActivityViewModel

class MainActivityViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
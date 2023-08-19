package com.example.brawlmobile

import android.app.Application
import com.example.brawlmobile.data.database.AppDatabase

class MainApplication : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
}
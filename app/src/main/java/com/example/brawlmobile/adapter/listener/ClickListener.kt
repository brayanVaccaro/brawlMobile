package com.example.brawlmobile.adapter

interface ClickListener {
    fun onClickViewInfo(model: Any){}
    fun onClickAddToFavourite(model: Any){}
    fun deleteFromFavourites(name: String){}
}
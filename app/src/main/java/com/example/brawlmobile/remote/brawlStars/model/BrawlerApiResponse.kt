package com.example.brawlmobile.remote.brawlStars.model

import com.example.brawlmobile.model.brawlStar.brawler.BrawlerModel

data class BrawlerApiResponse(
    val items: List<BrawlerModel>,
    val paging: Paging?
)
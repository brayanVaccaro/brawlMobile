package com.example.brawlmobile.brawlStar.remote.model

import com.example.brawlmobile.brawlStar.models.brawler.BrawlerModel

data class BrawlerApiResponse(
    val items: List<BrawlerModel>,
    val paging: Paging?
)

data class Paging(
    val cursors: Cursors?
)

data class Cursors(
    val name: String?
)
package com.example.brawlmobile.remote.model

import com.example.brawlmobile.models.BrawlerModel

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
package com.example.brawlmobile.remote.web

import com.example.brawlmobile.remote.web.factory.ToStringConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Remote {
    private const val BASE_URL = "https://brawlstars.fandom.com/"

    private val retrofitWeb = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ToStringConverterFactory())
        .build()

    val webService: WebService by lazy {
        retrofitWeb.create(WebService::class.java)
    }

}
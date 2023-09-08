package com.example.brawlmobile.remote.brawlStars

import com.example.brawlmobile.remote.brawlStars.web.factory.ToStringConverterFactory
import retrofit2.Retrofit

object RetrofitWeb {
    private const val BASE_URL = "https://brawlstars.fandom.com/"

    private val retrofitWeb = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ToStringConverterFactory())
        .build()

    val webService: WebService by lazy {
        retrofitWeb.create(WebService::class.java)
    }

}
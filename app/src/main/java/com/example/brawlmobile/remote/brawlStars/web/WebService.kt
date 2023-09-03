package com.example.brawlmobile.remote.brawlStars.web

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("wiki/{name}")
    suspend fun getTextFromWeb(
        @Path ("name") name: String
    ): Response<ResponseBody>
}
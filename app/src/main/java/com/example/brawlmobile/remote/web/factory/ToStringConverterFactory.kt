package com.example.brawlmobile.remote.web.factory

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

/**
 * Classe custom per convertire il corpo della risposta in una String
 */

class ToStringConverterFactory: Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return Converter<ResponseBody, String> { value ->
            try {
                value.string()
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
}
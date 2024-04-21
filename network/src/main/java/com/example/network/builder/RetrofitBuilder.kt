package com.example.network.builder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder(private val okHttpBuilder: OkHttpBuilder, private val baseUrl: String) {
    private val gsonConverterFactory: GsonConverterFactory by lazy { GsonConverterFactory.create() }
    val retrofit: Retrofit by lazy {
        Retrofit.Builder().client(okHttpBuilder.okHttpClient).baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory).build()
    }
}
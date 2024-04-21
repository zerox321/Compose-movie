package com.example.network.builder

import com.example.network.interceptor.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpBuilder(
    private val isDebug: Boolean,
    private val apiKey: String,
    private val connectionTimeOut: Long,
    private val readTimeOut: Long,
    private val writeTimeOut: Long
) {

    private val requestInterceptor by lazy { RequestInterceptor(apiKey = apiKey) }
    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = if (isDebug) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    val okHttpClient by lazy {
        OkHttpClient.Builder().apply {
            connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
            readTimeout(readTimeOut, TimeUnit.SECONDS)
            writeTimeout(writeTimeOut, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            addInterceptor(requestInterceptor)
        }.build()
    }
}
package com.example.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response


class RequestInterceptor(private val apiKey: String) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder().apply {
            addQueryParameter("api_key", apiKey)
        }
        val requestBuilder = originalRequest.newBuilder().url(url.build()).apply {
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
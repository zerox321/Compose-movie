package com.example.network.di

import com.example.network.BuildConfig.DEBUG
import com.example.network.BuildConfig.apiKey
import com.example.network.BuildConfig.baseUrl
import com.example.network.builder.NetworkServiceProvider
import com.example.network.builder.OkHttpBuilder
import com.example.network.builder.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpBuilder(): OkHttpBuilder {
        return OkHttpBuilder(
            apiKey = apiKey,
            isDebug = DEBUG,
            connectionTimeOut = 60,
            readTimeOut = 60,
            writeTimeOut = 2 * 60
        )
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpBuilder: OkHttpBuilder): RetrofitBuilder {
        return RetrofitBuilder(okHttpBuilder = okHttpBuilder, baseUrl = baseUrl)
    }

    @Provides
    @Singleton
    fun provideNetworkServiceProvider(retrofitBuilder: RetrofitBuilder): NetworkServiceProvider {
        return NetworkServiceProvider(retrofitBuilder = retrofitBuilder)
    }


}
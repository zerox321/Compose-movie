package com.example.banquemisrchallenge05.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.banquemisrchallenge05.ui.ToastController
import com.example.banquemisrchallenge05.ui.YoutubeLauncher
import com.example.core.controller.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideYoutubeLauncher(@ApplicationContext context: Context): YoutubeLauncher {
        return YoutubeLauncher(context = context)
    }

    @Provides
    @Singleton
    fun provideToastController(@ApplicationContext context: Context): ToastController {
        return ToastController(context = context)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityObserver(@ApplicationContext context: Context): NetworkConnectivityObserver {
        return NetworkConnectivityObserver(connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }


}
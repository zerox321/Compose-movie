package com.example.banquemisrchallenge05.di

import android.content.Context
import com.example.banquemisrchallenge05.ui.YoutubeLauncher
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

}
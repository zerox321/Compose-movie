package com.example.datasource.di

import com.example.core.repository.MovieRepository
import com.example.datasource.repository.MovieRepositoryImp
import com.example.network.builder.NetworkServiceProvider
import com.example.persistence.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        networkServiceProvider: NetworkServiceProvider, movieDao: MovieDao
    ): MovieRepository {
        return MovieRepositoryImp(
            movieService = networkServiceProvider.movieService,
            movieDao = movieDao,
            dispatcher = Dispatchers.IO
        )
    }

}
package com.example.datasource.di

import com.example.core.repository.MovieRepository
import com.example.datasource.usecase.GetKeywordList
import com.example.datasource.usecase.GetMovie
import com.example.datasource.usecase.GetMovieList
import com.example.datasource.usecase.GetReviewList
import com.example.datasource.usecase.GetVideoList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetMovieList(movieRepository: MovieRepository): GetMovieList {
        return GetMovieList(movieRepository = movieRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetVideoList(movieRepository: MovieRepository): GetVideoList {
        return GetVideoList(movieRepository = movieRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetReviewList(movieRepository: MovieRepository): GetReviewList {
        return GetReviewList(movieRepository = movieRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetKeywordList(movieRepository: MovieRepository): GetKeywordList {
        return GetKeywordList(movieRepository = movieRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetMovie(movieRepository: MovieRepository): GetMovie {
        return GetMovie(movieRepository = movieRepository)
    }

}
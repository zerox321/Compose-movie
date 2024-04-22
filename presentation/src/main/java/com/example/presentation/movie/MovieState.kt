package com.example.presentation.movie

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.example.core.model.entity.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Immutable
data class MovieState(
    val popularMovieList: Flow<PagingData<Movie>> = flow {},
    val upcomingMovieList: Flow<PagingData<Movie>> = flow {},
    val nowPlayingMovieList: Flow<PagingData<Movie>> = flow {},
    val error: String? = null
)
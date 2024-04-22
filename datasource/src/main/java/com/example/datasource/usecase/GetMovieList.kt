package com.example.datasource.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.model.entity.Movie
import com.example.core.repository.MovieRepository
import com.example.datasource.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow


class GetMovieList(private val movieRepository: MovieRepository) {
    operator fun invoke(movieType: String): Flow<PagingData<Movie>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2), pagingSourceFactory = {
            MoviePagingSource(movieRepository = movieRepository, movieType = movieType)
        }).flow

}
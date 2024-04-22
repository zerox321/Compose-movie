package com.example.datasource.usecase

import com.example.core.model.entity.Keyword
import com.example.core.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetKeywordList(private val movieRepository: MovieRepository) {
    operator fun invoke(movieId: Long): Flow<List<Keyword>> = flow {
        emit(movieRepository.fetchKeywords(movieId = movieId))
    }
}
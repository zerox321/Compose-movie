package com.example.datasource.usecase

import com.example.core.entity.Movie
import com.example.core.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetMovie(private val movieRepository: MovieRepository) {
     operator fun invoke(movieId: Long): Flow<Movie> = flow {
        emit(movieRepository.fetchMovie(movieId = movieId))
    }

}
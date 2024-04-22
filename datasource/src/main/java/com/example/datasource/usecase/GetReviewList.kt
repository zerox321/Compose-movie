package com.example.datasource.usecase

import com.example.core.model.entity.Review
import com.example.core.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetReviewList(private val movieRepository: MovieRepository) {
     operator fun invoke(movieId: Long): Flow<List<Review>> = flow {
        emit(movieRepository.fetchReviews(movieId = movieId))
    }

}
package com.example.datasource.usecase

import com.example.core.entity.Video
import com.example.core.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetVideoList(private val movieRepository: MovieRepository) {
    operator fun invoke(movieId: Long): Flow<List<Video>> = flow {
        emit(movieRepository.fetchVideos(movieId = movieId))
    }

}
package com.example.core.repository

import com.example.core.model.entity.Keyword
import com.example.core.model.entity.Movie
import com.example.core.model.entity.Review
import com.example.core.model.entity.Video

interface MovieRepository {
    suspend fun fetchKeywords(movieId: Long): List<Keyword>
    suspend fun fetchVideos(movieId: Long): List<Video>
    suspend fun fetchReviews(movieId: Long): List<Review>
    suspend fun fetchMovies(page: Int, movieType: String): List<Movie>
    suspend fun fetchMovie(movieId: Long): Movie


}
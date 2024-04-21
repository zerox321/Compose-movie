package com.example.core.repository

import com.example.core.entity.Keyword
import com.example.core.entity.Movie
import com.example.core.entity.Review
import com.example.core.entity.Video
import com.example.core.entity.remote.KeywordListResponse
import com.example.core.entity.remote.MovieListResponse
import com.example.core.entity.remote.ReviewListResponse
import com.example.core.entity.remote.VideoListResponse

interface MovieRepository {
    suspend fun fetchKeywords(movieId: Long): List<Keyword>
    suspend fun fetchVideos(movieId: Long): List<Video>
    suspend fun fetchReviews(movieId: Long): List<Review>
    suspend fun fetchMovies(page: Int, movieType: String): List<Movie>
    suspend fun fetchMovie(movieId: Long): Movie


}
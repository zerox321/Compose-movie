package com.example.network.service

import com.example.core.model.entity.Movie
import com.example.core.model.remote.KeywordListResponse
import com.example.core.model.remote.MovieListResponse
import com.example.core.model.remote.ReviewListResponse
import com.example.core.model.remote.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {
    @GET("/3/movie/{movie_id}/keywords")
    suspend fun fetchKeywords(@Path("movie_id") movieId: Long): KeywordListResponse

    @GET("/3/movie/{movie_id}/videos")
    suspend fun fetchVideos(@Path("movie_id") movieId: Long): VideoListResponse

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun fetchReviews(@Path("movie_id") movieId: Long): ReviewListResponse

    @GET("/3/movie/{movieType}")
    suspend fun fetchMovies(
        @Path("movieType") movieType: String,
        @Query("page") page: Int,
    ): MovieListResponse

}

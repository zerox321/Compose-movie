package com.example.core.entity.remote

import com.example.core.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
)
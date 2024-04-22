package com.example.core.model.remote

import com.example.core.model.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
)
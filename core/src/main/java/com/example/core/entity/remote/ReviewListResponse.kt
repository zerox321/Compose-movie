package com.example.core.entity.remote

import com.example.core.entity.Review
import com.google.gson.annotations.SerializedName

class ReviewListResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
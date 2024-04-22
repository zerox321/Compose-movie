package com.example.core.model.remote

import com.example.core.model.entity.Review
import com.google.gson.annotations.SerializedName

class ReviewListResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
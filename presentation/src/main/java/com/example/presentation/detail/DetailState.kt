package com.example.presentation.detail

import androidx.compose.runtime.Immutable
import com.example.core.model.entity.Keyword
import com.example.core.model.entity.Movie
import com.example.core.model.entity.Review
import com.example.core.model.entity.Video

@Immutable
data class DetailState(
    val movie: Movie? = null,
    val keywords: List<Keyword> = emptyList(),
    val videos: List<Video> = emptyList(),
    val reviews: List<Review> = emptyList(),
    val error: String? = null
)
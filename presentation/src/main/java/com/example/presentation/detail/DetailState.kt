package com.example.presentation.detail

import androidx.compose.runtime.Immutable
import com.example.core.entity.Keyword
import com.example.core.entity.Movie
import com.example.core.entity.Review
import com.example.core.entity.Video

@Immutable
data class DetailState(
    var movie: Movie? = null,
    val keywords: List<Keyword> = emptyList(),
    val videos: List<Video> = emptyList(),
    val reviews: List<Review> = emptyList(),
)
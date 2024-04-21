package com.example.presentation.detail

import androidx.compose.runtime.Immutable


@Immutable
sealed class DetailEvent {
    data object CloseDetailView : DetailEvent()
    data class FetchMovie(val movieId: Long) : DetailEvent()
    data class FetchVideos(val movieId: Long) : DetailEvent()
    data class FetchReviews(val movieId: Long) : DetailEvent()
    data class FetchKeywords(val movieId: Long) : DetailEvent()
    data class PlayVideo(val key: String) : DetailEvent()
}
package com.example.presentation.movie

import androidx.compose.runtime.Immutable
import com.example.core.model.entity.Movie

@Immutable
sealed class MovieEvent {
    data class OpenMovieDetails(val movieId: Long) : MovieEvent()
    data class FetchMovies(val type: MovieType) : MovieEvent()
}

@Immutable
enum class MovieType(val value: String) {
    Popular("popular"), NowPlaying("now_playing"), Upcoming("upcoming")
}

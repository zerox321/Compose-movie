package com.example.presentation.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.datasource.usecase.GetMovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMovieList: GetMovieList) : ViewModel() {
    var state by mutableStateOf(MovieState())
        private set

    private val _event = Channel<MovieEvent>()
    val event get() = _event.receiveAsFlow()

    fun sendEvent(event: MovieEvent) = viewModelScope.launch {
        _event.send(event)
    }

    init {
        sendEvent(event = MovieEvent.FetchMovies(MovieType.NowPlaying))
        sendEvent(event = MovieEvent.FetchMovies(MovieType.Popular))
        sendEvent(event = MovieEvent.FetchMovies(MovieType.Upcoming))
    }

    fun fetchMovieList(movieType: MovieType) {
        val movieList = getMovieList(movieType = movieType.value).cachedIn(viewModelScope)
            .distinctUntilChanged()
        state = when (movieType) {
            MovieType.NowPlaying -> state.copy(nowPlayingMovieList = movieList)
            MovieType.Popular -> state.copy(popularMovieList = movieList)
            MovieType.Upcoming -> state.copy(upcomingMovieList = movieList)
        }

    }

}
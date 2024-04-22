package com.example.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.datasource.usecase.GetMovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMovieList: GetMovieList) : ViewModel() {

    private val _state: MutableStateFlow<MovieState> by lazy { MutableStateFlow(MovieState()) }
    val currentState: StateFlow<MovieState> get() = _state.asStateFlow()

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
        _state.update {
            when (movieType) {
                MovieType.NowPlaying -> it.copy(nowPlayingMovieList = movieList)
                MovieType.Popular -> it.copy(popularMovieList = movieList)
                MovieType.Upcoming -> it.copy(upcomingMovieList = movieList)
            }
        }

    }

}
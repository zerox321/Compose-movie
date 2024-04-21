package com.example.presentation.detail


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.usecase.GetKeywordList
import com.example.datasource.usecase.GetMovie
import com.example.datasource.usecase.GetReviewList
import com.example.datasource.usecase.GetVideoList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getVideoList: GetVideoList,
    private val getReviewList: GetReviewList,
    private val getKeywordList: GetKeywordList,
    private val getMovie: GetMovie
) : ViewModel() {
    private val movieId by lazy {
        savedStateHandle.get<String>("MOVIE_ID")?.toLong()
            ?: throw (Exception("movieId is required"))
    }

    var state by mutableStateOf(DetailState())
        private set

    private val _event = Channel<DetailEvent>()
    val event get() = _event.receiveAsFlow()


    fun sendEvent(event: DetailEvent) = viewModelScope.launch {
        _event.send(event)
    }


    init {
        sendEvent(DetailEvent.FetchMovie(movieId = movieId))
        sendEvent(DetailEvent.FetchVideos(movieId = movieId))
        sendEvent(DetailEvent.FetchKeywords(movieId = movieId))
        sendEvent(DetailEvent.FetchReviews(movieId = movieId))
    }

    fun fetchMovie(movieId: Long) {
        getMovie(movieId = movieId).onEach { movie ->
            state = state.copy(movie = movie)
        }.launchIn(viewModelScope)
    }

    fun fetchReviewList(movieId: Long) {
        getReviewList(movieId = movieId).onEach { reviews ->
            state = state.copy(reviews = reviews)
        }.launchIn(viewModelScope)
    }

    fun fetchKeywordList(movieId: Long) {
        getKeywordList(movieId = movieId).onEach { keywords ->
            state = state.copy(keywords = keywords)
        }.launchIn(viewModelScope)
    }

    fun fetchVideoList(movieId: Long) {
        getVideoList(movieId = movieId).onEach { videos ->
            state = state.copy(videos = videos)
        }.launchIn(viewModelScope)
    }

}
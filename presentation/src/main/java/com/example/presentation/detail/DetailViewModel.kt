package com.example.presentation.detail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.usecase.GetKeywordList
import com.example.datasource.usecase.GetMovie
import com.example.datasource.usecase.GetReviewList
import com.example.datasource.usecase.GetVideoList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
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

    private val _state: MutableStateFlow<DetailState> by lazy { MutableStateFlow(DetailState()) }
    val currentState: StateFlow<DetailState> get() = _state.asStateFlow()

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
            _state.update { it.copy(movie = movie) }
        }.launchIn(viewModelScope)
    }

    fun fetchReviewList(movieId: Long) {
        getReviewList(movieId = movieId).onEach { reviews ->
            _state.update { it.copy(reviews = reviews) }
        }.catch { t -> _state.update { it.copy(error = t.message) } }.launchIn(viewModelScope)
    }

    fun fetchKeywordList(movieId: Long) {
        getKeywordList(movieId = movieId).onEach { keywords ->
            _state.update { it.copy(keywords = keywords) }
        }.catch { t -> _state.update { it.copy(error = t.message) } }.launchIn(viewModelScope)
    }

    fun fetchVideoList(movieId: Long) {
        getVideoList(movieId = movieId).onEach { videos ->
            _state.update { it.copy(videos = videos) }
        }.catch { t -> _state.update { it.copy(error = t.message) } }.launchIn(viewModelScope)
    }

}
package com.example.banquemisrchallenge05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.banquemisrchallenge05.ui.ToastController
import com.example.banquemisrchallenge05.ui.YoutubeLauncher
import com.example.banquemisrchallenge05.ui.navigation.NavScreen
import com.example.banquemisrchallenge05.ui.theme.BanquemisrChallenge05Theme
import com.example.presentation.detail.DetailEvent
import com.example.presentation.detail.DetailScreen
import com.example.presentation.detail.DetailViewModel
import com.example.presentation.movie.MovieEvent
import com.example.presentation.movie.MovieScreen
import com.example.presentation.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var youtubeLauncher: YoutubeLauncher
    @Inject
    lateinit var toastController: ToastController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BanquemisrChallenge05Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = NavScreen.MovieScreen.route) {
                        composable(NavScreen.MovieScreen.route) {
                            val moviesViewModel: MovieViewModel = hiltViewModel()

                            LaunchedEffect(key1 = Unit) {
                                moviesViewModel.event.collectLatest { event ->
                                    when (event) {
                                        is MovieEvent.FetchMovies -> moviesViewModel.fetchMovieList(
                                            event.type
                                        )

                                        is MovieEvent.OpenMovieDetails -> navController.navigate(
                                            NavScreen.MovieDetails.createRoute(
                                                movieId = event.movieId.toString()
                                            )
                                        )
                                    }
                                }
                            }

                            MovieScreen(state = moviesViewModel.state,
                                event = { event -> moviesViewModel.sendEvent(event = event) })
                        }
                        composable(NavScreen.MovieDetails.route) {
                            val detailViewModel: DetailViewModel = hiltViewModel()
                            LaunchedEffect(key1 = Unit) {
                                detailViewModel.event.collectLatest { event ->
                                    when (event) {
                                        DetailEvent.CloseDetailView -> navController.popBackStack()
                                        is DetailEvent.FetchMovie -> detailViewModel.fetchMovie(
                                            movieId = event.movieId
                                        )

                                        is DetailEvent.PlayVideo -> youtubeLauncher.watchYoutubeVideo(
                                            key = event.key
                                        )

                                        is DetailEvent.FetchKeywords -> detailViewModel.fetchKeywordList(
                                            movieId = event.movieId
                                        )

                                        is DetailEvent.FetchReviews -> detailViewModel.fetchReviewList(
                                            movieId = event.movieId
                                        )

                                        is DetailEvent.FetchVideos -> detailViewModel.fetchVideoList(
                                            movieId = event.movieId
                                        )
                                    }
                                }
                            }
                            detailViewModel.state.error?.let { error -> toastController.showMessage(message = error) }
                            DetailScreen(state = detailViewModel.state,
                                event = { event -> detailViewModel.sendEvent(event = event) })
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BanquemisrChallenge05Theme {}
}
package com.example.banquemisrchallenge05.ui.navigation


sealed class NavScreen(val route: String) {
    data object MovieScreen : NavScreen("Movie")
    data object MovieDetails : NavScreen("MovieDetails/{MOVIE_ID}") {
        fun createRoute(movieId: String): String = route.replace("{MOVIE_ID}", movieId)
    }

}

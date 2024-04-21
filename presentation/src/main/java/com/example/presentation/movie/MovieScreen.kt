package com.example.presentation.movie

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.presentation.R
import com.example.presentation.widget.MovieItem
import com.example.presentation.widget.ParallaxContainer


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieScreen(
    state: MovieState, event: (event: MovieEvent) -> Unit
) {
    val nowPlaying = state.nowPlayingMovieList.collectAsLazyPagingItems()
    val popular = state.popularMovieList.collectAsLazyPagingItems()
    val upcoming = state.upcomingMovieList.collectAsLazyPagingItems()

    ParallaxContainer(header = {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (search, title, notification, profile) = createRefs()

            Image(painter = painterResource(R.drawable.ic_man),
                contentDescription = "",
                modifier = Modifier.constrainAs(profile) {
                    top.linkTo(parent.top, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                }

            )
            Text(text = "Eslam Kamel", style = TextStyle(
                color = White, fontWeight = FontWeight.Bold, fontSize = 15.sp
            ), modifier = Modifier.constrainAs(title) {
                top.linkTo(profile.top)
                bottom.linkTo(profile.bottom)
                start.linkTo(profile.end, 8.dp)

            })
            Image(painter = painterResource(R.drawable.ic_notification),
                contentDescription = "",
                modifier = Modifier.constrainAs(notification) {
                    top.linkTo(profile.top)
                    bottom.linkTo(profile.bottom)
                    end.linkTo(parent.end, 16.dp)
                })
            Image(painter = painterResource(R.drawable.ic_search),
                contentDescription = "",
                modifier = Modifier.constrainAs(search) {
                    top.linkTo(profile.top)
                    bottom.linkTo(profile.bottom)
                    end.linkTo(notification.start, 8.dp)
                })


        }
    }, body = {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            if (nowPlaying.itemCount != 0) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.now_playing), style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow {
                    items(nowPlaying.itemCount) { index ->
                        MovieItem(movie = nowPlaying[index]!!, onItemClick = { selectedMovieId ->
                            event(
                                MovieEvent.OpenMovieDetails(selectedMovieId)
                            )
                        })
                    }
                }
            }
            if (popular.itemCount != 0) {

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.popular), style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow {
                    items(popular.itemCount) { index ->
                        MovieItem(movie = popular[index]!!, onItemClick = { selectedMovieId ->
                            event(
                                MovieEvent.OpenMovieDetails(selectedMovieId)
                            )
                        })
                    }
                }
            }
            if (upcoming.itemCount != 0) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.upcoming), style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow {
                    items(upcoming.itemCount) { index ->
                        MovieItem(movie = upcoming[index]!!, onItemClick = { selectedMovieId ->
                            event(
                                MovieEvent.OpenMovieDetails(selectedMovieId)
                            )
                        })
                    }
                }

            }
        }
    })

}



package com.example.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.movie.MovieEvent
import com.example.presentation.widget.KeywordItem
import com.example.presentation.widget.MovieHeader
import com.example.presentation.widget.NetworkImage
import com.example.presentation.widget.ParallaxContainer
import com.example.presentation.widget.ReviewItem
import com.example.presentation.widget.VideoItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(state: DetailState, event: (event: DetailEvent) -> Unit) {
    val movie = state.movie
    ParallaxContainer(header = {
        NetworkImage(url = movie?.backDropImage, modifier = Modifier.height(270.dp))
        Image(painter = painterResource(R.drawable.ic_back),
            contentDescription = "",
            modifier = Modifier
                .padding(20.dp)
                .clickable { event.invoke(DetailEvent.CloseDetailView) })
    }, body = {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            MovieHeader(movie = state.movie)
            if (state.videos.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.video),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    ),
                )
                LazyRow {
                    items(items = state.videos, itemContent = { video ->
                        VideoItem(video = video,
                            onVideoClick = { key -> event.invoke(DetailEvent.PlayVideo(key = key)) })
                    })

                }
            }
            if (state.keywords.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.keyword), style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow {
                    items(items = state.keywords, itemContent = { keyword ->
                        KeywordItem(keyword = keyword)
                    })

                }
            }
            if (state.reviews.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.review), style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                )

                repeat(state.reviews.size) { index ->
                    ReviewItem(review = state.reviews[index])
                }
            }
        }
    })


}


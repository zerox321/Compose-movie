package com.example.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.core.entity.Movie
import com.example.presentation.R

@Composable
fun MovieItem(movie: Movie, onItemClick: (selectedMovieId: Long) -> Unit) {
    ConstraintLayout(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .padding(4.dp)
        .clickable { onItemClick(movie.id) }

    ) {
        val (image, title, date, ratting, adault) = createRefs()
        NetworkImage(url = movie.posterImage,
            modifier = Modifier
                .clip(RoundedCornerShape(8))
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(title.top)
                }
                .width(140.dp)
                .height(200.dp))

        TextWithMinLines(text = movie.title, style = TextStyle(
            textAlign = TextAlign.Start, color = Color.White, fontSize = 14.sp
        ), minLines = 1, softWrap = true, modifier = Modifier.constrainAs(title) {
            top.linkTo(image.bottom, 8.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end, 4.dp)
            bottom.linkTo(date.top)

            width = Dimension.fillToConstraints
        })

        Text(text = movie.releaseDate!!, style = TextStyle(
            textAlign = TextAlign.Start, color = Color.White, fontSize = 12.sp
        ), modifier = Modifier.constrainAs(date) {
            top.linkTo(title.bottom, 4.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end, 4.dp)
            bottom.linkTo(ratting.top)
            width = Dimension.fillToConstraints
        })
        RatingBar(rating = movie.vote, modifier = Modifier.constrainAs(ratting) {
            top.linkTo(date.bottom, 4.dp)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom, 4.dp)
        })
        if (movie.adult) Image(painter = painterResource(R.drawable.ic_adault),
            contentDescription = null,
            modifier = Modifier.constrainAs(adault) {
                top.linkTo(parent.top, 4.dp)
                end.linkTo(parent.end, 4.dp)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieItem(movie = Movie(
        id = 1L,
        page = 0,
        posterPath = "",
        adult = false,
        video = false,
        overview = "",
        releaseDate = "",
        originalTitle = "",
        originalLanguage = "",
        title = "title",
        backdropPath = "backdropPath",
        popularity = 12f,
        voteAverage = 12f,
        voteCount = 12
    ), onItemClick = {})
}
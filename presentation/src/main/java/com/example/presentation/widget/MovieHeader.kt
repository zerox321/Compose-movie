package com.example.presentation.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.core.entity.Movie


@Composable
fun MovieHeader(movie: Movie?) {

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (posterImage, title, content, overview, genres, ratting) = createRefs()


        NetworkImage(url = movie?.posterImage,
            modifier = Modifier
                .clip(RoundedCornerShape(8))
                .constrainAs(posterImage) {
                    top.linkTo(parent.top, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                }
                .width(112.dp)
                .height(160.dp))
        TextWithMinLines(text = movie?.title ?: "", style = TextStyle(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 15.sp
        ), minLines = 1, softWrap = true, modifier = Modifier.constrainAs(title) {
            top.linkTo(posterImage.top, 8.dp)
            end.linkTo(parent.end, 4.dp)
            start.linkTo(posterImage.end, 8.dp)
            width = Dimension.fillToConstraints
        })

        Text(text = movie?.overview ?: "", style = TextStyle(
            textAlign = TextAlign.Start, color = Color.White, fontSize = 12.sp
        ), modifier = Modifier.constrainAs(overview) {
            top.linkTo(title.bottom, 6.dp)
            start.linkTo(posterImage.end, 8.dp)
            end.linkTo(parent.end, 4.dp)
            width = Dimension.fillToConstraints
        })

        Text(text = movie?.formattedContent ?: "", style = TextStyle(
            textAlign = TextAlign.Start, color = Color.White, fontSize = 12.sp
        ), modifier = Modifier.constrainAs(content) {
            top.linkTo(overview.bottom, 4.dp)
            start.linkTo(posterImage.end, 8.dp)
            end.linkTo(parent.end, 4.dp)
            width = Dimension.fillToConstraints
        })

        Text(text = movie?.genresName ?: "", style = TextStyle(
            textAlign = TextAlign.Start, color = Color.White, fontSize = 12.sp
        ), modifier = Modifier.constrainAs(genres) {
            top.linkTo(content.bottom, 4.dp)
            start.linkTo(posterImage.end, 8.dp)
            end.linkTo(parent.end, 4.dp)
            width = Dimension.fillToConstraints
        })

        RatingBar(rating = movie?.vote ?: 3f, modifier = Modifier.constrainAs(ratting) {
            top.linkTo(genres.bottom, 4.dp)
            start.linkTo(posterImage.end, 8.dp)
        })


    }
}

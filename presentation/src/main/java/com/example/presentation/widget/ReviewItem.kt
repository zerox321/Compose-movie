package com.example.presentation.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.core.model.entity.Movie
import com.example.core.model.entity.Review

@Composable
fun ReviewItem(review: Review?) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp)
    ) {
        val (name, value) = createRefs()
        Text(text = review?.author ?: "", style = TextStyle(
            textAlign = TextAlign.Start,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        ), modifier = Modifier.constrainAs(name) {
            top.linkTo(parent.top, 4.dp)
            start.linkTo(parent.start, 4.dp)
            end.linkTo(parent.end, 4.dp)
            width = Dimension.fillToConstraints
        })

        Text(text = review?.content ?: "", style = TextStyle(
            textAlign = TextAlign.Start, color = Color.White, fontSize = 13.sp
        ), modifier = Modifier.constrainAs(value) {
            top.linkTo(name.bottom, 4.dp)
            start.linkTo(parent.start, 8.dp)
            end.linkTo(parent.end, 8.dp)
            width = Dimension.fillToConstraints
        })
    }
}

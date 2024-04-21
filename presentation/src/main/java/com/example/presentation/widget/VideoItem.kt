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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.core.entity.Video
import com.example.presentation.R

@Composable
fun VideoItem(video: Video, onVideoClick: (key: String) -> Unit) {

    ConstraintLayout(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .padding(4.dp)
        .clickable { onVideoClick(video.key) }

    ) {
        val (image, playIcon, title) = createRefs()
        NetworkImage(url = video.thumbnail,
            modifier = Modifier
                .clip(RoundedCornerShape(8))
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 4.dp)
                    end.linkTo(parent.end, 4.dp)
                    bottom.linkTo(title.top)
                }
                .width(200.dp)
                .height(140.dp))

        Image(painter = painterResource(R.drawable.ic_play_view),
            contentDescription = "",
            modifier = Modifier.constrainAs(playIcon) {
                top.linkTo(image.top)
                start.linkTo(image.start)
                end.linkTo(image.end)
                bottom.linkTo(image.bottom)
            })

        Text(text = video.name, style = TextStyle(
            textAlign = TextAlign.Center, color = Color.White, fontSize = 12.sp
        ), modifier = Modifier.constrainAs(title) {
            top.linkTo(image.bottom, 4.dp)
            start.linkTo(parent.start, 2.dp)
            end.linkTo(parent.end, 2.dp)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        })

    }
}


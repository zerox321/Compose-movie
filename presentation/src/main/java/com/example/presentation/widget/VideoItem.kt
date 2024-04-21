package com.example.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
        val (image, playIcon) = createRefs()
        NetworkImage(url = video.thumbnail,
            modifier = Modifier
                .clip(RoundedCornerShape(8))
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 4.dp)
                    end.linkTo(parent.end, 4.dp)
                    bottom.linkTo(parent.bottom)
                }
                .width(200.dp)
                .height(140.dp))

        Image(painter = painterResource(R.drawable.ic_play_view),
            contentDescription = "",
            modifier = Modifier.constrainAs(playIcon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
    }
}


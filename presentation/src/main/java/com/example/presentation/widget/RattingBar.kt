package com.example.presentation.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Float,
    numStars: Int = 5,
    starSize: Dp = 16.dp,
    starSpacing: Dp = 2.dp,
    emptyStarColor: Color = Color.Gray,
    filledStarColor: Color = Color.Yellow,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
    ) {
        for (i in 0 until numStars) {
            val starColor = if (i < rating) filledStarColor else emptyStarColor
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.size(starSize)
            )
            if (i < numStars - 1) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}
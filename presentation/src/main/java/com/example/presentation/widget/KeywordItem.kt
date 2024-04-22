package com.example.presentation.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.model.entity.Keyword

@Composable
fun KeywordItem(keyword: Keyword) {

    Text(
        text = keyword.name, style = TextStyle(
            color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp
        ), modifier = Modifier.padding(start=4.dp)
    )

}
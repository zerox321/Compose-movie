package com.example.presentation.widget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TextWithMinLines(
    text: String,
    minLines: Int,
    style: TextStyle,
    softWrap: Boolean,
    modifier: Modifier
) {
    val lines = text.lines().take(minLines).toMutableList()
    while (lines.size < minLines) { lines.add("") }
    Text(
        text = lines.joinToString(separator = "\n"),
        maxLines = minLines,
        softWrap=softWrap,
        style=style,
        modifier=modifier,
        overflow = TextOverflow.Ellipsis,
    )
}
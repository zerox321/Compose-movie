package com.example.presentation.widget


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun ParallaxContainer(
    header: @Composable () -> Unit, body: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .wrapContentHeight()
            .graphicsLayer {
                translationY = (-scrollState.value / 2).toFloat()
            }) {
            header()
        }
        body()

    }
}
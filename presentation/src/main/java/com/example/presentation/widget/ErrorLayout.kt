package com.example.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorLayout(
    errorMessage: String,
    buttonText: String? = null,
    onClick: () -> Unit = { },
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            tint = Color.Red,
            contentDescription = "Error",
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp),
        )
        Text(
            text = errorMessage,
            modifier = Modifier.padding(16.dp),
        )
        buttonText?.let {
            Button(onClick = onClick) {
                Text(text = it)
            }
        }
    }
}



package com.example.core.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Video(
    val id: String,
    val name: String,
    val site: String,
    val key: String,
    val size: Int,
    val type: String
) {
    val thumbnail: String
        get() = "https://i.ytimg.com/vi/$key/hqdefault.jpg"
}
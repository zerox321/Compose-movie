package com.example.core.entity.remote

import com.example.core.entity.Video

data class VideoListResponse(
    val id: Int, val results: List<Video>
)
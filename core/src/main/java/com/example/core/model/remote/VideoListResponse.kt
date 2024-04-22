package com.example.core.model.remote

import com.example.core.model.entity.Video

data class VideoListResponse(
    val id: Int, val results: List<Video>
)
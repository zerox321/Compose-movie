package com.example.persistence.converters

import androidx.room.TypeConverter
import com.example.core.entity.Video
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class VideoListConverter {
    @TypeConverter
    fun fromString(value: String): List<Video>? {
        val listType = object : TypeToken<List<Video>>() {}.type
        return Gson().fromJson<List<Video>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Video>?): String {
        return Gson().toJson(list)
    }
}
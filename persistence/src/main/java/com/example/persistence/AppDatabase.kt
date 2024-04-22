package com.example.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.model.entity.Movie
import com.example.persistence.converters.IntegerListConverter
import com.example.persistence.converters.KeywordListConverter
import com.example.persistence.converters.ReviewListConverter
import com.example.persistence.converters.StringListConverter
import com.example.persistence.converters.VideoListConverter
import com.example.persistence.dao.MovieDao


@Database(
    entities = [(Movie::class)],
    version = 1, exportSchema = false
)
@TypeConverters(
    value = [
        (StringListConverter::class), (IntegerListConverter::class),
        (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
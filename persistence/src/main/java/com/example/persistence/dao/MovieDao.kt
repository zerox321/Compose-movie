package com.example.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movies: List<Movie>)


    @Query("Update Movie SET videos = :videos WHERE id = :id")
    suspend fun updateVideoList(id: Long, videos: String)

    @Query("Update Movie SET reviews = :reviews WHERE id = :id")
    suspend fun updateReviewsList(id: Long, reviews: String)

    @Query("Update Movie SET keywords = :keywords WHERE id = :id")
    suspend fun updateKeywordsList(id: Long, keywords: String)

    @Query("SELECT * FROM MOVIE WHERE id = :id")
    suspend fun getMovie(id: Long): Movie

    @Query("SELECT * FROM Movie WHERE page = :page AND movieType=:movieType ORDER BY id DESC")
    suspend fun getMovieList(page: Int, movieType: String): List<Movie>
}
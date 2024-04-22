package com.example.core.model.entity

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Immutable
@Entity(primaryKeys = [("id")])
data class Movie(
    var movieType: String? = null,
    var page: Int,
    var keywords: List<Keyword>? = emptyList(),
    var videos: List<Video>? = emptyList(),
    var reviews: List<Review>? = emptyList(),
    @SerializedName("poster_path") val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String? = "",
    @SerializedName("genre_ids") val genreIds: List<Int>? = emptyList(),
    val id: Long,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("original_language") val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    val popularity: Float,
    @SerializedName("vote_count") val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Float
) {
    val vote: Float get() = voteAverage / 2

    val posterImage get() = "https://image.tmdb.org/t/p/w500$posterPath"
    val backDropImage get() = "https://image.tmdb.org/t/p/w500$backdropPath"
    val formattedContent get() = "$releaseDate | $originalLanguage | $voteAverage / 10"
    val genresName get() = genreIds?.joinToString { getGenresName(it) }

    private fun getGenresName(gener: Int): String {
        return when (gener) {
            28 -> "Action"
            12 -> "Adventure"
            16 -> "Animation"
            35 -> "Comedy"
            80 -> "Crime"
            99 -> "Documentary"
            18 -> "Drama"
            10751 -> "Family"
            14 -> "Fantasy"
            36 -> "History"
            27 -> "Horror"
            10402 -> "Music"
            9648 -> "Mystery"
            10749 -> "Romance"
            878 -> "Science Fiction"
            10770 -> "TV Movie"
            53 -> "Thriller"
            10752 -> "War"
            37 -> "Western"
            else -> ""
        }

    }
}




package com.example.datasource.repository

import android.util.Log
import com.example.core.entity.Keyword
import com.example.core.entity.Movie
import com.example.core.entity.Review
import com.example.core.entity.Video
import com.example.core.repository.MovieRepository
import com.example.network.service.MovieService
import com.example.persistence.dao.MovieDao
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieRepositoryImp(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val dispatcher: CoroutineDispatcher
) : MovieRepository {
    override suspend fun fetchKeywords(movieId: Long): List<Keyword> = withContext(dispatcher) {
        val localMovie = fetchMovie(movieId = movieId)
        val localKeywordList = localMovie.keywords
        if (localKeywordList?.isNotEmpty() == true) return@withContext localKeywordList
        val remoteKeywordList = movieService.fetchKeywords(movieId = movieId).keywords
        localMovie.keywords = remoteKeywordList
        movieDao.updateKeywordsList(id = movieId, keywords = Gson().toJson(remoteKeywordList))
        remoteKeywordList
    }

    override suspend fun fetchVideos(movieId: Long): List<Video> = withContext(dispatcher) {
        val localMovie = fetchMovie(movieId = movieId)
        val localVideoList = localMovie.videos
        if (localVideoList?.isNotEmpty() == true) return@withContext localVideoList
        val remoteVideoList = movieService.fetchVideos(movieId = movieId).results
        localMovie.videos = remoteVideoList
        movieDao.updateVideoList(id = movieId, videos = Gson().toJson(remoteVideoList))
        remoteVideoList
    }

    override suspend fun fetchReviews(movieId: Long): List<Review> = withContext(dispatcher) {
        val localMovie = fetchMovie(movieId = movieId)
        val localReviewList = localMovie.reviews
        if (localReviewList?.isNotEmpty() == true) return@withContext localReviewList
        val remoteReviewList = movieService.fetchReviews(movieId = movieId).results
        localMovie.reviews = remoteReviewList
        movieDao.updateReviewsList(id = movieId, reviews = Gson().toJson(remoteReviewList))
        remoteReviewList
    }

    override suspend fun fetchMovies(page: Int, movieType: String): List<Movie> =
        withContext(dispatcher) {
            val localMovieList = movieDao.getMovieList(page = page, movieType = movieType)
            if (localMovieList.isNotEmpty()) return@withContext localMovieList
            val remoteMovieList =
                movieService.fetchMovies(page = page, movieType = movieType).results
            remoteMovieList.forEach {
                it.page = page
                it.movieType = movieType
            }
            movieDao.insertMovieList(movies = remoteMovieList)
            return@withContext remoteMovieList
        }

    override suspend fun fetchMovie(movieId: Long): Movie = withContext(dispatcher) {
        return@withContext movieDao.getMovie(id = movieId)
    }
}
package com.example.datasource.repository

import com.example.core.model.entity.Keyword
import com.example.core.model.entity.Movie
import com.example.core.model.entity.Review
import com.example.core.model.entity.Video
import com.example.network.service.MovieService
import com.example.persistence.dao.MovieDao
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieRepositoryImpTest {
    @get:Rule
    val mockKRule = MockKRule(this)

    private val dispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var movieService: MovieService

    @MockK
    private lateinit var movieDao: MovieDao



    private lateinit var underTest: MovieRepositoryImp

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        underTest = MovieRepositoryImp(movieService, movieDao, dispatcher)
    }

    @Test
    fun shouldFetchKeyWordsFromNetworkAndUpdateLocal() = runTest {
        val movieId = 123L
        val movie = mockkClass(Movie::class)
        every { movie.keywords } returns emptyList()
        val expected = listOf<Keyword>(Keyword(0, "keyword1"), Keyword(1, "keyword2"))
        every { movie.keywords = expected } returns Unit
        coEvery { movieDao.getMovie(movieId) } returns movie
        coEvery { movieService.fetchKeywords(movieId).keywords } returns expected
        val keyWordsJson = Gson().toJson(expected)
        coEvery { movieDao.updateKeywordsList(movieId, keyWordsJson) } returns Unit
        val actual =  underTest.fetchKeywords(movieId)
        assertEquals(expected, actual)
        coVerify { movieDao.updateKeywordsList(movieId, keyWordsJson) }
    }


    @Test
    fun shouldReturnLocalKeywordsGivenTheyAreFound() = runTest {
        val movieId = 123L
        val movie = mockkClass(Movie::class)
        val expected = listOf<Keyword>(Keyword(0, "keyword1"), Keyword(1, "keyword2"))
        coEvery { movieDao.getMovie(movieId) } returns movie
        every { movie.keywords } returns expected
        val keyWordsJson = Gson().toJson(expected)
        val actual =  underTest.fetchKeywords(movieId)
        assertEquals(expected, actual)
        coVerify(exactly = 0) { movieService.fetchKeywords(movieId) }
        coVerify(exactly = 0) { movieDao.updateKeywordsList(movieId, keyWordsJson) }
    }

    @Test
    fun shouldFetchVideosFromNetworkAndUpdateLocal() = runTest {
        val movieId = 123L
        val movie = mockkClass(Movie::class)
        every { movie.videos } returns emptyList()
        val expected = listOf<Video>(
            Video("0", "name1", "site1", "key1", 20, "type1"),
            Video("1", "name2", "site2", "key2", 20, "type2")
        )
        every { movie.videos = expected } returns Unit
        coEvery { movieDao.getMovie(movieId) } returns movie
        coEvery { movieService.fetchVideos(movieId).results } returns expected
        val videosJson = Gson().toJson(expected)
        coEvery { movieDao.updateVideoList(movieId, videosJson) } returns Unit
        val actual =  underTest.fetchVideos(movieId)
        assertEquals(expected, actual)
        coVerify { movieDao.updateVideoList(movieId, videosJson) }
    }

    @Test
    fun shouldReturnLocalVideosGivenTheyAreFound() = runTest {
        val movieId = 123L
        val movie = mockkClass(Movie::class)
        val expected = listOf<Video>(
            Video("0", "name1", "site1", "key1", 20, "type1"),
            Video("1", "name2", "site2", "key2", 20, "type2")
        )
        coEvery { movieDao.getMovie(movieId) } returns movie
        every { movie.videos } returns expected
        val videosJson = Gson().toJson(expected)
        val actual =  underTest.fetchVideos(movieId)
        assertEquals(expected, actual)
        coVerify(exactly = 0) { movieService.fetchVideos(movieId) }
        coVerify(exactly = 0) { movieDao.updateVideoList(movieId, videosJson) }
    }

    @Test
    fun shouldFetchReviewsFromNetworkAndUpdateLocal() = runTest {
        val movieId = 123L
        val movie = mockkClass(Movie::class)
        every { movie.reviews } returns emptyList()
        val expected = listOf(
            Review("0", "author1", "content1", "url1"),
            Review("1", "author2", "content2", "url2")
        )
        every { movie.reviews = expected } returns Unit
        coEvery { movieDao.getMovie(movieId) } returns movie
        coEvery { movieService.fetchReviews(movieId).results } returns expected
        val videosJson = Gson().toJson(expected)
        coEvery { movieDao.updateReviewsList(movieId, videosJson) } returns Unit
        val actual =  underTest.fetchReviews(movieId)
        assertEquals(expected, actual)
        coVerify { movieDao.updateReviewsList(movieId, videosJson) }
    }

    @Test
    fun shouldReturnLocalReviewsGivenTheyAreFound() = runTest {
        val movieId = 123L
        val movie = mockkClass(Movie::class)
        every { movie.reviews } returns emptyList()
        val expected = listOf(
            Review("0", "author1", "content1", "url1"),
            Review("1", "author2", "content2", "url2")
        )
        coEvery { movieDao.getMovie(movieId) } returns movie
        every { movie.reviews } returns expected
        val videosJson = Gson().toJson(expected)
        val actual =  underTest.fetchReviews(movieId)
        assertEquals(expected, actual)
        coVerify(exactly = 0) { movieService.fetchReviews(movieId) }
        coVerify(exactly = 0) { movieDao.updateReviewsList(movieId, videosJson) }
    }

    @Test
    fun shouldFetchMoviesFromNetworkAndUpdateLocal() = runTest {
        val page = 1
        val type = "Action"
        val expected = listOf(mockkClass(Movie::class), mockkClass(Movie::class))
        coEvery { movieDao.getMovieList(page, type) } returns emptyList()
        expected.forEach {
            every { it.page = page } returns Unit
            every { it.movieType = type } returns Unit
        }
        coEvery { movieService.fetchMovies(type, page).results } returns expected
        coEvery { movieDao.insertMovieList(expected) } returns Unit
        val actual =  underTest.fetchMovies(page, type)
        assertEquals(expected, actual)
        coVerify { movieDao.insertMovieList(expected) }
    }

    @Test
    fun shouldReturnLocalMoviesGivenTheyAreFound() = runTest {
        val page = 1
        val type = "Action"
        val expected = listOf(mockkClass(Movie::class), mockkClass(Movie::class))
        coEvery { movieDao.getMovieList(page, type) } returns expected

        val actual =  underTest.fetchMovies(page, type)
        assertEquals(expected, actual)
        coVerify(exactly = 0) { movieService.fetchMovies(type, page) }
        coVerify(exactly = 0) { movieDao.insertMovieList(expected) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}
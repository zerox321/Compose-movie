package com.example.datasource.usecase

import app.cash.turbine.test
import com.example.core.model.entity.Video
import com.example.core.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
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
class GetVideoListTest {
    @get:Rule
    val mockKRule = MockKRule(this)

    private val dispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var movieRepository: MovieRepository



    private lateinit var underTest: GetVideoList

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        underTest = GetVideoList(movieRepository)
    }


    @Test
    fun shouldGetReviews() = runTest {
        val movieId = 123L
        val expected = listOf<Video>(
            Video("0", "name1", "site1", "key1", 20, "type1"),
            Video("1", "name2", "site2", "key2", 20, "type2")
        )
        coEvery { movieRepository.fetchVideos(movieId) } returns expected

        underTest.invoke(movieId).test {
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }

        coVerify { movieRepository.fetchVideos(movieId) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}
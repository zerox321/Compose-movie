package com.example.datasource.usecase

import app.cash.turbine.test
import com.example.core.model.entity.Review
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
class GetReviewListTest {
    @get:Rule
    val mockKRule = MockKRule(this)

    private val dispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var movieRepository: MovieRepository



    private lateinit var underTest: GetReviewList

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        underTest = GetReviewList(movieRepository)
    }


    @Test
    fun shouldGetReviews() = runTest {
        val movieId = 123L
        val expected = listOf(
            Review("0", "author1", "content1", "url1"),
            Review("1", "author2", "content2", "url2")
        )
        coEvery { movieRepository.fetchReviews(movieId) } returns expected

        underTest.invoke(movieId).test {
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }

        coVerify { movieRepository.fetchReviews(movieId) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}
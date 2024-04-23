package com.example.datasource.usecase

import androidx.paging.Logger
import app.cash.turbine.test
import com.example.core.model.entity.Movie
import com.example.core.repository.MovieRepository
import com.example.datasource.utils.toPagination
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
class GetMovieListTest {
    @get:Rule
    val mockKRule = MockKRule(this)

    private val dispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var movieRepository: MovieRepository

    @MockK
    private lateinit var logger: Logger

    private lateinit var underTest: GetMovieList

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        underTest = GetMovieList(movieRepository)
    }


    @Test
    fun shouldGetMovieList() = runTest {
        val page = 1
        val type = "Action"
        val expected = listOf(mockkClass(Movie::class), mockkClass(Movie::class))

        coEvery { movieRepository.fetchMovies(page, type) } returns expected
        every { logger.isLoggable(any()) } returns false
        underTest.invoke(type).test {
            val pagination = awaitItem().toPagination(logger)
            assertEquals(expected, pagination.first())
            cancelAndConsumeRemainingEvents()
        }

        coVerify { movieRepository.fetchMovies(page, type) }
    }



    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}
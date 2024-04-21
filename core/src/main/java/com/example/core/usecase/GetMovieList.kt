package com.example.core.usecase

import com.example.core.entity.remote.MovieListResponse
import com.example.core.repository.MovieRepository


class GetMovieList(private val repo: MovieRepository) {
    suspend operator fun invoke(page: Int): MovieListResponse = repo.fetchMovies(page = page)
}
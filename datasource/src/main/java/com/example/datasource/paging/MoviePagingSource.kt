package com.example.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.entity.Movie
import com.example.core.repository.MovieRepository

class MoviePagingSource(
    private val movieType: String, private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val movies = movieRepository.fetchMovies(movieType = movieType, page = currentPage)
            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.isEmpty()) null else currentPage + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = state.anchorPosition

}


package com.example.network.builder

import com.example.network.service.MovieService
import javax.inject.Inject


class NetworkServiceProvider @Inject constructor(private val retrofitBuilder: RetrofitBuilder) {

    val movieService: MovieService by lazy { retrofitBuilder.retrofit.create(MovieService::class.java) }


}
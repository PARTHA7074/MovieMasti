package com.partha.creation.retrofit

import com.partha.creation.pojo.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("manga/fetch")
    suspend fun fetchMovies(@Query("page") page: String): Response<MovieResponse>
}
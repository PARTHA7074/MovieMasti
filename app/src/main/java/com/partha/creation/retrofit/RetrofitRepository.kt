package com.partha.creation.retrofit

import com.partha.creation.pojo.MovieResponse
import retrofit2.Response

class RetrofitRepository {
    suspend fun fetchManga(page: String): Response<MovieResponse> {
        return RetrofitInstance.api.fetchMovies(page)
    }
}
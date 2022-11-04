package com.example.demo.data

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomApi {
    @GET("integers/?num=1&col=1&base=10&format=plain")
    suspend fun get(
        @Query("min") min: Int,
        @Query("max") max: Int,
    ): String
}

package com.azat.data.service

import com.azat.domain.entity.Property
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/homes.json")
    suspend fun getProperties(): Response<List<Property>>
}
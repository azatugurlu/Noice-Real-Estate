package com.azat.data.datasource

import com.azat.data.service.ApiService
import com.azat.domain.Output
import com.azat.domain.entity.Property
import retrofit2.Retrofit
import javax.inject.Inject

class PropertiesRemoteDataSource @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {
    suspend fun fetchProperties(): Output<List<Property>> {
        return getResponse(
            request = { apiService.getProperties() },
            defaultErrorMessage = "Error fetching Properties"
        )
    }
}
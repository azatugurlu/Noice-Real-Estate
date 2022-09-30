package com.azat.domain.repository

import com.azat.domain.Output
import com.azat.domain.entity.Property
import kotlinx.coroutines.flow.Flow

interface PropertiesRepository {
    suspend fun fetchProperties(): Flow<Output<List<Property>>>
    fun getProperty(id: Int): Property?
}
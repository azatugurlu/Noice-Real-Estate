package com.azat.domain.usecases

import com.azat.domain.Output
import com.azat.domain.entity.Property
import kotlinx.coroutines.flow.Flow

interface PropertiesUseCases {
    suspend fun execute(): Flow<Output<List<Property>>>
    fun getProperty(id: Int): Property?
}
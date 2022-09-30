package com.azat.data.repository

import com.azat.data.datasource.PropertiesRemoteDataSource
import com.azat.domain.Output
import com.azat.domain.entity.Property
import com.azat.domain.repository.PropertiesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.delay
import javax.inject.Inject

internal class PropertiesRepositoryImpl @Inject constructor(
    private val propertiesRemoteDataSource: PropertiesRemoteDataSource
) : PropertiesRepository {

    private var properties: List<Property> = emptyList()

    override suspend fun fetchProperties(): Flow<Output<List<Property>>> {
        return flow {
            emit(Output.loading())
            delay(1000)
            val result = propertiesRemoteDataSource.fetchProperties()
            emit(result)
            properties = result.data.orEmpty()
        }.flowOn(Dispatchers.IO)
    }

    override fun getProperty(id: Int): Property? {
        return properties.firstOrNull { it.id == id }
    }
}
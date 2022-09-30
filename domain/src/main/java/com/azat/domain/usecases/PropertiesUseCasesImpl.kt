package com.azat.domain.usecases

import com.azat.domain.entity.Property
import com.azat.domain.repository.PropertiesRepository
import com.azat.domain.Output
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


internal class PropertiesUseCasesImpl @Inject constructor(
    private val charsRepository: PropertiesRepository
) : PropertiesUseCases {
    override suspend fun execute(): Flow<Output<List<Property>>> {
        return charsRepository.fetchProperties()
    }

    override fun getProperty(id: Int): Property? {
        return charsRepository.getProperty(id)
    }
}

package com.azat.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.azat.domain.entity.Property
import com.azat.domain.repository.PropertiesRepository
import com.azat.domain.usecases.PropertiesUseCases
import com.azat.domain.usecases.PropertiesUseCasesImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PropertiesUseCasesTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var propertiesRepository: PropertiesRepository

    private lateinit var propertiesUseCases: PropertiesUseCases

    private val getDummyProperties = listOf(
        Property(
            id = 1,
            price = "10",
            type = "kerrostalo",
            buildingYear = "2010",
            location = "espoo",
            size = "100",
            roomSize = "4",
            roomDefinition = "4",
            description = "gg",
            featureImage = "link",
            images = listOf()
        )
    )

    @Before
    fun setUp() {
        propertiesUseCases = PropertiesUseCasesImpl(propertiesRepository)
    }

    @Test
    fun testFetchPropertiesShouldSuccess() = runBlocking {
        val inputFlow = flowOf(Output.success(getDummyProperties))
        whenever(propertiesRepository.fetchProperties()).thenReturn(inputFlow)
        val output = propertiesUseCases.execute().toList()
        assert(output[0].data?.size == 1)
    }
}
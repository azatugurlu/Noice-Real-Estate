package com.azat.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.azat.data.datasource.PropertiesRemoteDataSource
import com.azat.data.getDummyProperties
import com.azat.domain.Output
import com.azat.domain.repository.PropertiesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class PropertiesRepositoryTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var propertiesRepository: PropertiesRepository

    @Mock
    lateinit var propertiesRemoteDataSource: PropertiesRemoteDataSource


    @Before
    fun setUp() {
        propertiesRepository = PropertiesRepositoryImpl(propertiesRemoteDataSource)
    }

    @Test
    fun testFetchPropertiesShouldReturnSuccess() = runBlocking {
        val propertiesOutput = Output.success(getDummyProperties)
        val inputFlow = listOf(Output.loading(), Output.success(propertiesOutput))
        whenever(propertiesRemoteDataSource.fetchProperties()).thenReturn(propertiesOutput)
        val outputFlow = propertiesRepository.fetchProperties().toList()
        assert(outputFlow.size == 2)
        assert(inputFlow[0] == outputFlow[0])
    }
}
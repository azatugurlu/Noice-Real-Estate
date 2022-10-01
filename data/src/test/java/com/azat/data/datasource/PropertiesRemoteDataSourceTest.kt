package com.azat.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.azat.data.getDummyProperties
import com.azat.data.service.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PropertiesRemoteDataSourceTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var apiService: ApiService

    private lateinit var propertiesRemoteDataSource: PropertiesRemoteDataSource

    @Before
    fun setUp() {
        propertiesRemoteDataSource = PropertiesRemoteDataSource(apiService, retrofit)
    }

    @Test
    fun testFetchPropertiesShouldSucceed() = runBlocking {
        whenever(apiService.getProperties()).thenReturn(Response.success(getDummyProperties))
        val fetchedProperties = propertiesRemoteDataSource.fetchProperties()
        assert(fetchedProperties.data?.size == getDummyProperties.size)
    }

    @Test
    fun testFetchPropertiesShouldFail() = runBlocking {
        val mockitoException = MockitoException("Unknown Error")
        whenever(apiService.getProperties()).thenThrow(mockitoException)
        val fetchedProperties = propertiesRemoteDataSource.fetchProperties()
        assert(fetchedProperties.message == "Unknown Error")
    }

    @Test
    fun testFetchPropertiesShouldReturnServerError() = runBlocking {
        whenever(apiService.getProperties())
            .thenReturn(Response.error(500, "".toResponseBody()))
        val fetchedProperties = propertiesRemoteDataSource.fetchProperties()
        assert(fetchedProperties.message == "Unknown Error")
    }
}
package com.azat.data.di

import com.azat.data.repository.PropertiesRepositoryImpl
import com.azat.domain.repository.PropertiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun bindPropertiesRepository(
        repository: PropertiesRepositoryImpl
    ): PropertiesRepository
}
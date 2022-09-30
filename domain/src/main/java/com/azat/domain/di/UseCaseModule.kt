package com.azat.domain.di

import com.azat.domain.usecases.PropertiesUseCases
import com.azat.domain.usecases.PropertiesUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    internal abstract fun bindPropertiesUseCases(
        useCaseImpl: PropertiesUseCasesImpl
    ): PropertiesUseCases
}
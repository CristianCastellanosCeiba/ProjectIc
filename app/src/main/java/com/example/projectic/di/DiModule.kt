package com.example.projectic.di

import android.content.Context
import com.example.domain.repository.AutoRepository
import com.example.domain.repository.MotorcycleRepository
import com.example.infrastructure.repository.AutoRepositoryImpl
import com.example.infrastructure.repository.MotorcycleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiModule {

    @Provides
    @Singleton
    fun injRepository(@ApplicationContext context: Context) : AutoRepository = AutoRepositoryImpl(context)

    @Provides
    @Singleton
    fun injRepositoryMoto(@ApplicationContext context: Context) : MotorcycleRepository = MotorcycleRepositoryImpl(context)

}
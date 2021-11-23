package com.example.projectic.di

import com.example.domain.repository.AutoRepository
import com.example.domain.repository.MotorcycleRepository
import com.example.infrastructure.repository.AutoRepositoryImpl
import com.example.infrastructure.repository.MotorcycleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class DiModule {

    @Binds
    abstract fun bindAutoViewModel(
        autoRepositoryImpl: AutoRepositoryImpl
    ): AutoRepository

    @Binds
    abstract fun bindMotorcycleViewModel(
        motorcycleRepositoryImpl: MotorcycleRepositoryImpl
    ): MotorcycleRepository

}
package com.example.projectic.viewModel

import com.example.domain.repository.AutoRepository
import com.example.domain.repository.MotorcycleRepository
import com.example.domain.service.AutoServices
import com.example.domain.service.MotorcycleServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class Di {

    @Provides
    @ViewModelScoped
    fun findAutoServices(autoRepository: AutoRepository) = AutoServices(autoRepository)

    @Provides
    @ViewModelScoped
    fun findMotorcyclesServices(motorcycleRepository: MotorcycleRepository) = MotorcycleServices(motorcycleRepository)
}
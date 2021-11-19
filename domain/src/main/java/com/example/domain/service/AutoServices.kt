package com.example.domain.service

import com.example.domain.entity.Vehicle
import com.example.domain.repository.AutoRepository

class AutoServices(
    private val autoRepository: AutoRepository
) {
    suspend fun registryAuto(vehicle: Vehicle) {
        autoRepository.registryAuto(vehicle)
    }

    suspend fun exitAuto(registration: String) {
        autoRepository.exitAuto(registration)
    }
}

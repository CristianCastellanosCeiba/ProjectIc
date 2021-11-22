package com.example.domain.service

import com.example.domain.entity.Motorcycle
import com.example.domain.repository.MotorcycleRepository

class MotorcycleServices(private val motorcycleRepository: MotorcycleRepository) {
    suspend fun registryMotorcycle(motorcycle: Motorcycle) {
        motorcycleRepository.registryMotorcycle(motorcycle)
    }

    suspend fun registryMotorcycle(registration: String) {
        motorcycleRepository.exitMotorcycle(registration)
    }
}

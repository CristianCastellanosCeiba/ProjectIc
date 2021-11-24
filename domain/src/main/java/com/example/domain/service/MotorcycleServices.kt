package com.example.domain.service

import com.example.domain.entity.Motorcycle
import com.example.domain.repository.MotorcycleRepository
import javax.inject.Inject

//class MotorcycleServices @Inject constructor(private val motorcycleRepository: MotorcycleRepository) {
class MotorcycleServices (private val motorcycleRepository: MotorcycleRepository) {
    suspend fun registryMotorcycle(motorcycle: Motorcycle) = motorcycleRepository.registryMotorcycle(motorcycle)

    suspend fun exitMotorcycle(registration: String) = motorcycleRepository.exitMotorcycle(registration)

    suspend fun payment(registration: String) = motorcycleRepository.payment(registration)

    suspend fun getMotorcycles(): Int = motorcycleRepository.getMotorcycles()

    suspend fun getListMotorcycles(): Motorcycle.MotorcycleList = motorcycleRepository.getListMotorcycles()
}

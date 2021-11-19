package com.example.domain.service

import com.example.domain.entity.Vehicle
import com.example.domain.repository.VehicleRepository

class VehicleServices(
    private val vehicleRepository: VehicleRepository
) {
    suspend fun registryVehicle(vehicle: Vehicle) {
        vehicleRepository.registryVehicle(vehicle)
    }

    suspend fun exitVehicle(registration: String) {
        vehicleRepository.exitVehicle(registration)
    }
}

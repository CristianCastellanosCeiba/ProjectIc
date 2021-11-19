package com.example.domain.repository

import com.example.domain.entity.Vehicle

interface VehicleRepository {
    suspend fun registryVehicle(vehicle: Vehicle)
    suspend fun exitVehicle(registration: String)
}
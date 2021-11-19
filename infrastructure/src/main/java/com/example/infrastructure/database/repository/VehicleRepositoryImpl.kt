package com.example.infrastructure.database.repository

import android.content.Context
import com.example.domain.entity.Vehicle
import com.example.domain.repository.VehicleRepository

class VehicleRepositoryImpl(context: Context): VehicleRepository {
    override suspend fun registryVehicle(vehicle: Vehicle) {
        TODO("Not yet implemented")
    }

    override suspend fun exitVehicle(vehicle: Vehicle) {
        TODO("Not yet implemented")
    }
}
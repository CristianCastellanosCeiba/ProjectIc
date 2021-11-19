package com.example.infrastructure.repository

import android.content.Context
import com.example.domain.entity.Vehicle
import com.example.domain.repository.VehicleRepository
import com.example.infrastructure.anticorruption.VehicleTranslator
import com.example.infrastructure.database.VehicleDb

class VehicleRepositoryImpl(context: Context): VehicleRepository {

    private val vehicleDb: VehicleDb = VehicleDb.getDataBase(context)

    override suspend fun registryVehicle(vehicle: Vehicle) {
        val vehicleEntity = vehicleDb.vehicleDao().saveVehicles(
            VehicleTranslator().fromDomainToEntity(vehicle)
        )
    }

    override suspend fun exitVehicle(registration: String) {
        vehicleDb.vehicleDao().deleteVehicle(registration)
    }
}
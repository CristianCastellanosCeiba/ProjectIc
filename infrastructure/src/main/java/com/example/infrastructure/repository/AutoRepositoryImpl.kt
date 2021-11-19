package com.example.infrastructure.repository

import android.content.Context
import com.example.domain.entity.Vehicle
import com.example.domain.repository.AutoRepository
import com.example.infrastructure.anticorruption.VehicleTranslator
import com.example.infrastructure.database.VehicleDb

class AutoRepositoryImpl(context: Context): AutoRepository {

    private val vehicleDb: VehicleDb = VehicleDb.getDataBase(context)

    override suspend fun registryAuto(vehicle: Vehicle) {
        val vehicleEntity = vehicleDb.vehicleDao().saveVehicles(
            VehicleTranslator().fromDomainToEntity(vehicle)
        )
    }

    override suspend fun exitAuto(registration: String) {
        vehicleDb.vehicleDao().deleteVehicle(registration)
    }
}
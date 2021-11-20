package com.example.infrastructure.repository

import android.content.Context
import com.example.domain.entity.Auto
import com.example.domain.repository.AutoRepository
import com.example.infrastructure.anticorruption.AutoTranslator
import com.example.infrastructure.database.VehicleDb
import com.example.infrastructure.database.dao.AutoDao

class AutoRepositoryImpl(context: Context): AutoRepository {

    private val vehicleDb: VehicleDb = VehicleDb.getDataBase(context)

    override suspend fun registryAuto(auto: Auto) {
        vehicleDb.vehicleDao().saveVehicles(
            AutoTranslator().fromDomainToEntity(auto)
        )
    }

    override suspend fun exitAuto(registration: String) {
        vehicleDb.vehicleDao().deleteVehicle(registration)
    }
}
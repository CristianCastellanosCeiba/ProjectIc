package com.example.infrastructure.repository

import android.content.Context
import com.example.domain.entity.Auto
import com.example.domain.repository.AutoRepository
import com.example.infrastructure.anticorruption.AutoTranslator
import com.example.infrastructure.database.VehicleDb
import com.example.infrastructure.database.entity.toAutoList

class AutoRepositoryImpl (context: Context): AutoRepository {

    private val vehicleDb: VehicleDb = VehicleDb.getDataBase(context)

    override suspend fun registryAuto(auto: Auto) {
        vehicleDb.vehicleDao().saveVehicles(
            AutoTranslator().fromDomainToEntity(auto)
        )
    }

    override suspend fun exitAuto(registration: String) {
        vehicleDb.vehicleDao().deleteVehicle(registration)
    }

    override suspend fun payment(registration: String): Long {
        val autoData = AutoTranslator().fromEntityToDomain(vehicleDb.vehicleDao().getVehicle(registration))
        return autoData.calculatePay()
    }

    override suspend fun getAutos(): Int {
        return vehicleDb.vehicleDao().getCountVehicles()
    }

    override suspend fun getListAutos(): Auto.AutoList {
        return vehicleDb.vehicleDao().getVehicles().toAutoList()
    }
}
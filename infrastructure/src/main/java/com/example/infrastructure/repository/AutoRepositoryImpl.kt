package com.example.infrastructure.repository

import android.content.Context
import com.example.domain.entity.Auto
import com.example.domain.repository.AutoRepository
import com.example.infrastructure.anticorruption.AutoTranslator
import com.example.infrastructure.database.VehicleDb
import java.util.*

class AutoRepositoryImpl(context: Context): AutoRepository {

    private val vehicleDb: VehicleDb = VehicleDb.getDataBase(context)

    override suspend fun registryAuto(auto: Auto, firstLetter: String) {
        try {
            if (auto.validateLetter(firstLetter)) {
                vehicleDb.vehicleDao().saveVehicles(
                    AutoTranslator().fromDomainToEntity(auto)
                )
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun exitAuto(registration: String) {
        vehicleDb.vehicleDao().deleteVehicle(registration)
    }

    override suspend fun payment(registration: String, hourExit: Date) {
        val autoData = AutoTranslator().fromEntityToDomain(vehicleDb.vehicleDao().getVehicle(registration))
        autoData
    }
}
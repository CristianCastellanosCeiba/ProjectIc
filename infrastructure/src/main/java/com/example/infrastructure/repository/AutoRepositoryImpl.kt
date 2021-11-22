package com.example.infrastructure.repository

import android.content.Context
import com.example.domain.entity.Auto
import com.example.domain.repository.AutoRepository
import com.example.infrastructure.exception.NotDeleteCar
import com.example.infrastructure.anticorruption.AutoTranslator
import com.example.infrastructure.database.VehicleDb

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
        try {
            vehicleDb.vehicleDao().deleteVehicle(registration)
        } catch (e: Exception) {
            throw NotDeleteCar("Error al borrar el auto")
        }

    }

    override suspend fun payment(registration: String): Long {
        val autoData = AutoTranslator().fromEntityToDomain(vehicleDb.vehicleDao().getVehicle(registration))
        return autoData.calculatePayment()
    }

    override suspend fun getAutos(): Int {
        return vehicleDb.vehicleDao().getAllVehicles()
    }
}
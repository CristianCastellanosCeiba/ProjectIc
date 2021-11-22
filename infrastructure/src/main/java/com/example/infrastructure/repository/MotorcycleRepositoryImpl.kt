package com.example.infrastructure.repository

import android.content.Context
import com.example.domain.entity.Motorcycle
import com.example.domain.repository.MotorcycleRepository
import com.example.infrastructure.exception.NotDeleteCar
import com.example.infrastructure.anticorruption.MotorcycleTranslator
import com.example.infrastructure.database.VehicleDb
import com.example.infrastructure.exception.NotFoundRegister

class MotorcycleRepositoryImpl(context: Context): MotorcycleRepository {

    private val vehicleDb: VehicleDb = VehicleDb.getDataBase(context)

    override suspend fun registryMotorcycle(motorcycle: Motorcycle) {
        vehicleDb.vehicleDao().saveMotorcycles(
            MotorcycleTranslator().fromDomainToEntity(motorcycle)
        )
    }

    override suspend fun exitMotorcycle(registration: String) {
        try {
            vehicleDb.vehicleDao().deleteMotorcycle(registration)
        } catch (e: Exception) {
            throw NotDeleteCar("Error al borrar la moto")
        }

    }

    override suspend fun payment(registration: String): Long {
        try {
            val motorcycleData = MotorcycleTranslator().fromEntityToDomain(
                vehicleDb.vehicleDao().getMotorcycle(registration)
            )
            return motorcycleData.calculatePayment()
        } catch (e: Exception) {
            throw NotFoundRegister("Registro no encontrado")
        }

    }

    override suspend fun getMotorcycles(): Int {
        return vehicleDb.vehicleDao().getAllMotorcycles()
    }
}
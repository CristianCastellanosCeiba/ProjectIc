package com.example.infrastructure.repository

import android.content.Context
import com.example.domain.entity.Motorcycle
import com.example.domain.repository.MotorcycleRepository
import com.example.infrastructure.anticorruption.MotorcycleTranslator
import com.example.infrastructure.database.VehicleDb
import com.example.infrastructure.database.entity.toMotorcycleList

//class MotorcycleRepositoryImpl @Inject constructor(@ActivityContext context: Context): MotorcycleRepository {
class MotorcycleRepositoryImpl (context: Context): MotorcycleRepository {

    private val vehicleDb: VehicleDb = VehicleDb.getDataBase(context)

    override suspend fun registryMotorcycle(motorcycle: Motorcycle) = vehicleDb.vehicleDao().saveMotorcycles(MotorcycleTranslator().fromDomainToEntity(motorcycle))

    override suspend fun exitMotorcycle(registration: String) = vehicleDb.vehicleDao().deleteMotorcycle(registration)

    override suspend fun payment(registration: String): Long {
        val motorcycleData = MotorcycleTranslator().fromEntityToDomain(
            vehicleDb.vehicleDao().getMotorcycle(registration)
        )
        return motorcycleData.calculatePay()
    }

    override suspend fun getMotorcycles(): Int = vehicleDb.vehicleDao().getCountMotorcycles()

    override suspend fun getListMotorcycles(): Motorcycle.MotorcycleList {
        return vehicleDb.vehicleDao().getMotorcycles().toMotorcycleList()
    }
}
package com.example.infrastructure.anticorruption

import com.example.domain.entity.Vehicle
import com.example.infrastructure.database.entity.VehicleEntity
import java.util.*

class VehicleTranslator {
    fun fromDomainToEntity(vehicle: Vehicle): VehicleEntity {
        return VehicleEntity(
            registration = vehicle.registration,
            hourEntry = vehicle.hourEntry.toString(),
            hourExit = vehicle.hourExit.toString(),
            type = vehicle.type
        )
    }

    fun fromEntityToDomain(vehicleEntity: VehicleEntity): Vehicle {
        return Vehicle(
            vehicleEntity.registration,
            Date(vehicleEntity.hourEntry),
            Date(vehicleEntity.hourExit),
            type = vehicleEntity.type
        )
    }
}
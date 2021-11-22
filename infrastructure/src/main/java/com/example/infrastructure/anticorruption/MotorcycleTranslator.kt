package com.example.infrastructure.anticorruption

import com.example.domain.entity.Motorcycle
import com.example.infrastructure.database.entity.MotorcycleEntity
import java.util.*

class MotorcycleTranslator {
    fun fromDomainToEntity(motorcycle: Motorcycle): MotorcycleEntity {
        return MotorcycleEntity(
            motorcycle.registration,
            motorcycle.hourEntry.time,
            motorcycle.type,
            motorcycle.cylinder
        )
    }

    fun fromEntityToDomain(motorcycleEntity: MotorcycleEntity): Motorcycle {
        return Motorcycle(
            motorcycleEntity.cylinder,
            motorcycleEntity.registration,
            motorcycleEntity.type,
            Date(motorcycleEntity.hourEntry),
        )
    }
}
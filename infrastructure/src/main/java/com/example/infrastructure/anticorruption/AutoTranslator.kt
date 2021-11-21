package com.example.infrastructure.anticorruption

import com.example.domain.entity.Auto
import com.example.domain.entity.Motorcycle
import com.example.infrastructure.database.entity.AutoEntity
import java.util.*

class AutoTranslator {
    fun fromDomainToEntity(auto: Auto): AutoEntity {
        return AutoEntity(
            auto.registration,
            auto.hourEntry.toString(),
            auto.hourExit.toString(),
            auto.type
        )
    }

    fun fromEntityToDomain(autoEntity: AutoEntity): Auto {
        return Auto(
            autoEntity.registration,
            Date(autoEntity.hourEntry),
            Date(autoEntity.hourExit),
            autoEntity.type
        )
    }
}
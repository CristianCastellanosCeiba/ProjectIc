package com.example.infrastructure.anticorruption

import com.example.domain.entity.Auto
import com.example.infrastructure.database.entity.AutoEntity

class AutoTranslator {
    fun fromDomainToEntity(auto: Auto): AutoEntity {
        return AutoEntity(
            auto.registration,
            auto.hourEntry.toString(),
            auto.hourExit.toString(),
            auto.type
        )
    }

    fun fromEntityToDomain(auto: Auto): Auto {
        return Auto(
            auto.registration,
            auto.hourEntry,
            auto.hourExit,
            type = auto.type
        )
    }
}
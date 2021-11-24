package com.example.domain.service

import com.example.domain.entity.Auto
import com.example.domain.exception.NoEntryDay
import com.example.domain.repository.AutoRepository
import javax.inject.Inject

//class AutoServices @Inject constructor(
class AutoServices (
    private val autoRepository: AutoRepository
) {

    suspend fun registryAuto(auto: Auto) {
        try {
            if (auto.validateLetter()) {
                autoRepository.registryAuto(auto)
            } else {
                throw NoEntryDay("Ingreso permitido los Lunes y Domingos")
            }
        } catch (e: NoEntryDay) {
            throw e
        }
    }

    suspend fun exitAuto(registration: String) = autoRepository.exitAuto(registration)

    suspend fun payment(registration: String) = autoRepository.payment(registration)

    suspend fun getAutos(): Int = autoRepository.getAutos()

    suspend fun getListAutos(): Auto.AutoList = autoRepository.getListAutos()
}

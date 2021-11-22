package com.example.domain.service

import com.example.domain.entity.Auto
import com.example.domain.repository.AutoRepository

class AutoServices(
    private val autoRepository: AutoRepository
) {
    suspend fun registryAuto(auto: Auto, firstLetter: String) = autoRepository.registryAuto(auto, firstLetter)

    suspend fun exitAuto(registration: String) = autoRepository.exitAuto(registration)
}

package com.example.domain.repository

import com.example.domain.entity.Vehicle

interface AutoRepository {
    suspend fun registryAuto(vehicle: Vehicle)
    suspend fun exitAuto(registration: String)
}
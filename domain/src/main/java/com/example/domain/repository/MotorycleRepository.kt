package com.example.domain.repository

import com.example.domain.entity.Motorcycle

interface MotorcycleRepository {
    suspend fun registryMotorcycle(motorcycle: Motorcycle)
    suspend fun exitMotorcycle(registration: String)
    suspend fun payment(registration: String): Long
    suspend fun getMotorcycles(): Int
    suspend fun getListMotorcycles(): Motorcycle.MotorcycleList
}
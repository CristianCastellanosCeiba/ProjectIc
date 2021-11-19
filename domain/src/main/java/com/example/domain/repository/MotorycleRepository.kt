package com.example.domain.repository

import com.example.domain.entity.Motorcycle

interface MotorycleRepository {
    suspend fun registryMotorcycle(motorcycle: Motorcycle)
    suspend fun exitMotorcycle(registration: String)
}
package com.example.domain.repository

import com.example.domain.entity.Auto
import java.util.*

interface AutoRepository {
    suspend fun registryAuto(auto: Auto)
    suspend fun exitAuto(registration: String)
    suspend fun payment(registration: String): Long
    suspend fun getAutos(): Int
}
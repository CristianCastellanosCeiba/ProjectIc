package com.example.domain.repository

import com.example.domain.entity.Auto

interface AutoRepository {
    suspend fun registryAuto(auto: Auto)
    suspend fun exitAuto(registration: String)
}
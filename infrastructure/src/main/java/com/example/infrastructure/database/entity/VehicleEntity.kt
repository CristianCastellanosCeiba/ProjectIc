package com.example.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.Motorcycle

@Entity
data class VehicleEntity(
    @PrimaryKey
    val id: String = "",
    @ColumnInfo(name = "name")
    val registration: String = "",
    @ColumnInfo(name = "name")
    val motorcycle: String = ""
)

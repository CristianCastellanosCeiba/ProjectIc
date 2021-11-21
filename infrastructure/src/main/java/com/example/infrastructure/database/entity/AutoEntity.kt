package com.example.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AutoEntity(
    @PrimaryKey
    val registration: String,
    @ColumnInfo(name = "hourEntry")
    val hourEntry: Long,
    @ColumnInfo(name = "type")
    val type: String
)

package com.example.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.Motorcycle
import java.util.*

@Entity
data class MotorcycleEntity(
    @PrimaryKey
    val registration: String,
    @ColumnInfo(name = "hourEntry")
    val hourEntry: Long,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "cylinder")
    val cylinder: Int
)

fun List<MotorcycleEntity>.toMotorcycleList(): Motorcycle.MotorcycleList {
    val resultList = mutableListOf<Motorcycle>()
    this.forEach { motorcycleEntity ->
        resultList.add(motorcycleEntity.toMotorcycle())
    }
    return Motorcycle.MotorcycleList(resultList)
}

fun MotorcycleEntity.toMotorcycle(): Motorcycle = Motorcycle(
    this.cylinder,
    this.registration,
    this.type,
    Date(this.hourEntry)
)
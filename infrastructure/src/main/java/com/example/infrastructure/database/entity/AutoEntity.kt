package com.example.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.Auto
import java.util.*

@Entity
data class AutoEntity(
    @PrimaryKey
    val registration: String,
    @ColumnInfo(name = "hourEntry")
    val hourEntry: Long,
    @ColumnInfo(name = "type")
    val type: String
)

fun List<AutoEntity>.toAutoList(): Auto.AutoList {
    val resultList = mutableListOf<Auto>()
    this.forEach { autoEntity ->
        resultList.add(autoEntity.toAuto())
    }
    return Auto.AutoList(resultList)
}

fun AutoEntity.toAuto(): Auto = Auto(
    this.registration,
    Date(this.hourEntry),
    this.type
)
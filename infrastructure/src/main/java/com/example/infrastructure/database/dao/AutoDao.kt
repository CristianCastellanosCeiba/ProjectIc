package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infrastructure.anticorruption.AutoTranslator
import com.example.infrastructure.database.entity.AutoEntity

@Dao
interface AutoDao {
    @Query("SELECT COUNT(*) FROM AutoEntity")
    suspend fun getAllVehicles(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVehicles(auto: AutoEntity)

    @Query("DELETE FROM AutoEntity WHERE registration= :registration")
    suspend fun deleteVehicle(registration: String)

    @Query("SELECT * FROM AutoEntity WHERE registration= :registration")
    suspend fun getVehicle(registration: String): AutoEntity
}
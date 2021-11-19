package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infrastructure.database.entity.VehicleEntity

@Dao
interface VehicleDao {
    @Query("SELECT COUNT(*) FROM VehicleEntity")
    suspend fun getAllVehicles(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVehicles(vehicle: VehicleEntity)

    @Query("DELETE FROM VehicleEntity WHERE registration = :registration")
    suspend fun deleteVehicle(registration: String)
}
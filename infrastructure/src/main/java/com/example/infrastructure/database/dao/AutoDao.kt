package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.infrastructure.database.entity.AutoEntity
import com.example.infrastructure.database.entity.MotorcycleEntity

@Dao
interface AutoDao {
    @Query("SELECT COUNT(*) FROM AutoEntity")
    suspend fun getCountVehicles(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVehicles(auto: AutoEntity)

    @Query("DELETE FROM AutoEntity WHERE registration= :registration")
    suspend fun deleteVehicle(registration: String)

    @Query("SELECT * FROM AutoEntity WHERE registration= :registration")
    suspend fun getVehicle(registration: String): AutoEntity

    @Query("SELECT * FROM AutoEntity")
    suspend fun getVehicles(): List<AutoEntity>

    @Query("SELECT COUNT(*) FROM MotorcycleEntity")
    suspend fun getCountMotorcycles(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMotorcycles(moto: MotorcycleEntity)

    @Query("DELETE FROM MotorcycleEntity WHERE registration= :registration")
    suspend fun deleteMotorcycle(registration: String)

    @Query("SELECT * FROM MotorcycleEntity WHERE registration= :registration")
    suspend fun getMotorcycle(registration: String): MotorcycleEntity

    @Query("SELECT * FROM MotorcycleEntity")
    suspend fun getMotorcycles(): List<MotorcycleEntity>
}
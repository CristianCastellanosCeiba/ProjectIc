package com.example.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VehicleDao {
    @Query("SELECT * FROM ")
}
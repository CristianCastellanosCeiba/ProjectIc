package com.example.infrastructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.infrastructure.database.dao.VehicleDao
import com.example.infrastructure.database.entity.VehicleEntity

@Database(entities = [VehicleEntity::class], version = 1)
abstract class VehicleDb: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao

    companion object {
        private var INSTANCE: VehicleDb? = null

        fun getDataBase(context: Context): VehicleDb {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                VehicleDb::class.java,
                "vehicles_table"
            ).build()
            return INSTANCE!!
        }
    }
}
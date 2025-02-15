package com.hashan0314.babykicks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BabyMovement::class], version = 1, exportSchema = false)
abstract class BabyMovementDatabase: RoomDatabase() {
    abstract fun babyMovementDao(): BabyMovementDao

    companion object{
        @Volatile
        private var INSTANCE: BabyMovementDatabase? = null

        fun getDatabase(context: Context):BabyMovementDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BabyMovementDatabase::class.java,
                    "baby_movement_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
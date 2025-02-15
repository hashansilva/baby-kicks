/*
 * Copyright (c) 2025. Hashan Silva
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 */

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
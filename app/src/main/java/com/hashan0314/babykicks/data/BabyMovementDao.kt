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

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BabyMovementDao {

    @Insert
    suspend fun insertMovement(movement: BabyMovement)

    @Query("SELECT * from baby_movement ORDER BY timestamp DESC")
    fun getAllMovements(): Flow<List<BabyMovement>>

    @Delete
    suspend fun deleteMovement(movement: BabyMovement)

    @Update
    suspend fun updateMovement(movement: BabyMovement)
}
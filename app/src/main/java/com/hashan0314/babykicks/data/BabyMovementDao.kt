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
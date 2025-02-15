package com.hashan0314.babykicks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "baby_movement")
data class BabyMovement(
    @PrimaryKey(autoGenerate=true)
    val id:Int =0,
    val timestamp:String
)

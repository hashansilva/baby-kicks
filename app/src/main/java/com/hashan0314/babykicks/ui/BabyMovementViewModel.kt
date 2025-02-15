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

package com.hashan0314.babykicks.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hashan0314.babykicks.data.BabyMovement
import com.hashan0314.babykicks.data.BabyMovementDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BabyMovementViewModel(application: Application) : AndroidViewModel(application) {
    private val db = BabyMovementDatabase.getDatabase(application).babyMovementDao()
    val movements = db.getAllMovements()

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    fun recordMovement() {
        val currentDateTime =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        viewModelScope.launch {
            db.insertMovement(BabyMovement(timestamp = currentDateTime))
            _toastMessage.value = "Movement recorded at $currentDateTime"
        }
    }

    fun deleteMovement(movement: BabyMovement){
        viewModelScope.launch {
            db.deleteMovement(movement)
            _toastMessage.value = "Record deleted!"
        }
    }

    fun updateMovement(movement: BabyMovement, newTimestamp: String) {
        viewModelScope.launch {
            db.updateMovement(movement.copy(timestamp = newTimestamp))
            _toastMessage.value = "Movement updated!"
        }
    }

    fun clearToastMessage(){
        _toastMessage.value = null
    }
}
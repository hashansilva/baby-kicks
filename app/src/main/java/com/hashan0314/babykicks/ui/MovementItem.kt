package com.hashan0314.babykicks.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hashan0314.babykicks.data.BabyMovement

@Composable
fun MovementItem(movement: BabyMovement, viewModel: BabyMovementViewModel) {
    var isEditing by remember { mutableStateOf(false) }
    var newTimestamp by remember { mutableStateOf(movement.timestamp) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isEditing) {
                OutlinedTextField(
                    value = newTimestamp,
                    onValueChange = { newTimestamp = it },
                    label = { Text("Edit Timestamp") },
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = {
                    viewModel.updateMovement(movement, newTimestamp)
                    isEditing = false
                }) { Icon(Icons.Default.Check, contentDescription = "Save") }
                IconButton(
                    onClick = { isEditing = false }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Cancel",
                        tint = Color(MaterialTheme.colorScheme.error.value)
                    )
                }
            } else {
                Text(
                    text = movement.timestamp,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { isEditing = true }
                ) { Icon(Icons.Default.Edit, contentDescription = "Edit") }
                IconButton(
                    onClick = { viewModel.deleteMovement(movement) }
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color(MaterialTheme.colorScheme.error.value)
                    )
                }

            }
        }
    }
}
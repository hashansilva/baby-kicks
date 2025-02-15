package com.hashan0314.babykicks.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(selectedScreen: String, onItemSelected: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedScreen == "home",
            onClick = { onItemSelected("home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = selectedScreen == "history",
            onClick = { onItemSelected("history") },
            icon = { Icon(Icons.Default.List, contentDescription = "History") },
            label = { Text("History") }
        )
    }
}
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

package com.hashan0314.babykicks

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hashan0314.babykicks.ui.BabyMovementViewModel
import com.hashan0314.babykicks.ui.BottomNavigationBar
import com.hashan0314.babykicks.ui.MainScreen
import com.hashan0314.babykicks.ui.MovementHistoryScreen
import com.hashan0314.babykicks.ui.theme.BabyKicksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BabyKicksTheme {
                val navController = rememberNavController()
                var selectedScreen by remember { mutableStateOf("home") }

                val viewModel: BabyMovementViewModel by viewModels()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            selectedScreen = selectedScreen,
                            onItemSelected = { screen ->
                                selectedScreen = screen
                                navController.navigate(screen) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { MainScreen(viewModel) }
                        composable("history") { MovementHistoryScreen(viewModel) }
                    }
                }
            }
        }
    }
}
package com.hashan0314.babykicks.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hashan0314.babykicks.R

@Composable
fun MainScreen(viewModel: BabyMovementViewModel) {
    val context = LocalContext.current
    val toastMessage by viewModel.toastMessage.collectAsState()

    LaunchedEffect(toastMessage) {
        toastMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.clearToastMessage()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize() // Makes the image take up the full screen
            .clickable { viewModel.recordMovement() } // Clickable area covers entire screen
    ) {
        Image(
            painter = painterResource(id = R.drawable.button_image), // Replace with your image
            contentDescription = "Full-screen Button Image",
            modifier = Modifier.fillMaxSize() // Image fills the entire screen
        )
    }
}


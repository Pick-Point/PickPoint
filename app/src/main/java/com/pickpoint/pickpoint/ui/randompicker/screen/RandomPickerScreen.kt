package com.pickpoint.pickpoint.ui.randompicker.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun RandomPickerScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "RandomPickerScreen", fontSize = 30.sp)
    }
}
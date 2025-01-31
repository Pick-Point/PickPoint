package com.pickpoint.pickpoint.ui.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "HomeScreen", fontSize = 30.sp)
    }
}